package votingsystem.voting;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class FormController {

    private final VoterRepositery formDataRepository;

    public FormController(VoterRepositery formDataRepository) {
        this.formDataRepository = formDataRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerVoter(@RequestBody Voter newVoter) {
        newVoter.voteKeyGenretor();
        if(formDataRepository.existsById(newVoter.getidNo()))
        {
            return ResponseEntity.ok("already regsitered");
        }
        else{
            Voter registered=formDataRepository.save(newVoter); 
            return ResponseEntity.ok(registered.getfName()+" "+registered.getlName());
        }
        // return ResponseEntity.ok("cxz");

    }
    
}
