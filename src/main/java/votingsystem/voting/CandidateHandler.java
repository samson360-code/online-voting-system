package votingsystem.voting;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CandidateHandler {
    @Autowired
    private final CandidateDataBase candidatesDb;
    // public ModelAndView modelandview = new ModelAndView("/candidatePost");

    public CandidateHandler(CandidateDataBase candidatesDb) {
        this.candidatesDb = candidatesDb;
    }

    @PostMapping("/postCandidate")
    public ModelAndView postMethodName(@ModelAttribute Candidate newCandidate) {
        ModelAndView modelandview = new ModelAndView("redirect:/candidatePost");
        modelandview.addObject("candidate", null);
        Optional<Candidate> optionalcandidate = candidatesDb.getCandidate(newCandidate.getcandId());
        if (optionalcandidate.isPresent()) {
            Candidate alreaady = optionalcandidate.get();
            modelandview.addObject("candidate", alreaady.getfname() + alreaady.getlname()
                    + " the candidate already appointed with ID " + newCandidate.getcandId());
        } else {
            newCandidate = candidatesDb.saveDb(newCandidate);
            modelandview.addObject("candidate", newCandidate.getfname() + newCandidate.getlname()
                    + " is Regsterd Succesfully with ID " + newCandidate.getcandId());
        }
        return modelandview;
    }

    @RequestMapping(value = { "/candidatePost", "/candidatePost" }, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView candidatePoster(HttpSession session) {
        ModelAndView modelandview = new ModelAndView("/candidatePost");
        if (session.getAttribute("voterId") != null) {
            String isSessionON = (String) session.getAttribute("voterId");
            modelandview.addObject("isSession", isSessionON);
            List<Candidate> candidates = candidatesDb.getllCandidate();
            modelandview.addObject("candidates", candidates);
            return modelandview;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
    }

    @PostMapping("/delete")
    public ModelAndView deleteCandidate(@RequestParam("deleteID") String candID) {
        ModelAndView modelandview = new ModelAndView("redirect:/candidatePost");
        candidatesDb.deleteCandidate(candID);
        return modelandview;
    }

    @PostMapping("/update")
    public ModelAndView editCandidate(@RequestParam("EditID") String candID) {
        ModelAndView modelandview = new ModelAndView("/candidatePost");
        Optional<Candidate> optionalcandidate = candidatesDb.getCandidate(candID);
        if (optionalcandidate.isPresent()) {
            Candidate editedCand = optionalcandidate.get();
            modelandview.addObject("editedCand", editedCand);
            List<Candidate> candidates = candidatesDb.getllCandidate();
            modelandview.addObject("candidates", candidates);
        }
        return modelandview;
    }

    @PostMapping("/editcan")
    public ModelAndView edit(@ModelAttribute Candidate newCandidate) {
        ModelAndView modelandview = new ModelAndView("redirect:/candidatePost");
        newCandidate = candidatesDb.saveDb(newCandidate);
        modelandview.addObject("candidate", newCandidate.getfname() + newCandidate.getlname()
                + " is Edited Succesfully with ID " + newCandidate.getcandId());
        return modelandview;
    }

}
