package votingsystem.voting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class VotePageController {
    private final CandidateDataBase candidatesDb;
    private final VoterCrud voter;

    public VotePageController(CandidateDataBase candidatesDb, VoterCrud voter) {
        this.candidatesDb = candidatesDb;
        this.voter = voter;
    }

@RequestMapping(value = { "/index", "/index" }, method = { RequestMethod.GET, RequestMethod.POST }) 
   public ModelAndView homeDisplayer(HttpSession session) {
        ModelAndView candiModelView = new ModelAndView("/index");
        String isSessionON= (String) session.getAttribute("voterId");
        candiModelView.addObject("isSession", isSessionON);
        if (session.getAttribute("voterId") != null) {
            boolean isvote= (boolean) session.getAttribute("isvoted");
            candiModelView.addObject("isvoted", isvote);
        }
        List<Candidate> candidates = candidatesDb.getllCandidate();
        candiModelView.addObject("candidates", candidates);
        return candiModelView;
    }

    @PostMapping("/done")
    public ResponseEntity<String> done(HttpSession session) {
        if (session.getAttribute("voterId") != null) {
            String voterId = (String) session.getAttribute("voterId");
            return ResponseEntity.ok(voterId);

        } else {
            return ResponseEntity.ok("302");

        }
    }
    
    @PostMapping("/confrimed")
    public ResponseEntity<String> ConfiremVoted(@RequestBody String candID, HttpSession session) {
        if (session.getAttribute("voterId") != null) {
            Optional<Candidate> optobeVoted = candidatesDb.getCandidate(candID);
            if (optobeVoted.isPresent()) {
                Candidate tobeVoted = optobeVoted.get();
                tobeVoted.setvotedBy();
                candidatesDb.saveDb(tobeVoted);
                String voterId = (String) session.getAttribute("voterId");
                Optional<Voter> voterOptional = voter.getVoter(voterId);
                if (voterOptional.isPresent()) {
                    Voter voted = voterOptional.get();
                    voted.setisVoted(true);
                    voter.saveVoter(voted);
                    return ResponseEntity.ok(candidatesDb.saveDb(tobeVoted).getfname() + " "
                            + candidatesDb.saveDb(tobeVoted).getlname());
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("errorPage  of inner");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("errorPage");
    }
}
