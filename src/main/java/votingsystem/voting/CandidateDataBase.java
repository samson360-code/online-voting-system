package votingsystem.voting;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CandidateDataBase {
    private final  CandidateRepositery candidatedata;

    public CandidateDataBase(CandidateRepositery candidateRep) {
        this.candidatedata = candidateRep;
    }

    public List<Candidate> getllCandidate() {
        return candidatedata.findAll();
    }

    public Optional<Candidate> getCandidate(String candID) {
        Optional<Candidate> candidateOptional = candidatedata.findById(candID);
        return candidateOptional;
    }

    public Candidate  saveDb(Candidate tobe) {
       Candidate saved= candidatedata.save(tobe);
        return saved;
    }
    public void deleteCandidate(String candID){
        candidatedata.deleteById(candID);

    }

}
