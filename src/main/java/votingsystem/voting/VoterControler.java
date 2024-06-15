package votingsystem.voting;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

@Controller
@Service
public class VoterControler {
    private final VoterCrud voter;
    private Voter toSign;

    public VoterControler(VoterCrud formDataRepository) {
        this.voter = formDataRepository;
    }

    @GetMapping("/login")
    public String indexDisplayer() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @PostMapping("/signIn")
    public ModelAndView postMethodName(@RequestParam("IdNumber") String IdNumber,
            @RequestParam("password") String password, HttpSession session) {
        Optional<Voter> optionalVoter = voter.getVoter(IdNumber);
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", null);
        if (optionalVoter.isPresent()) {
            toSign = optionalVoter.get();
            if (toSign.getpassword().equals(password)) {
                session.setAttribute("voterId", toSign.getidNo());
                session.setAttribute("isvoted", toSign.getisVoted());
                if (toSign.getidNo().equals("AD1234")) {
                    modelAndView.setViewName("redirect:/candidatePost");
                } else
                    modelAndView.setViewName("redirect:/index");
                return modelAndView;
            }
            else {
                modelAndView.addObject("message", "incorrect password!");
                return modelAndView;
            }
        } else {
            modelAndView.addObject("message", " user is not registered!!");
            return modelAndView;

        }
    }
}
