package votingsystem.voting;

import java.util.Optional;

import org.springframework.stereotype.Service;
@Service
public class VoterCrud {
    private final  VoterRepositery voterEntity;


    public VoterCrud(VoterRepositery voterEntity) {
        this.voterEntity = voterEntity;
    }
   public  Optional<Voter>  getVoter(String idNo) {


        return voterEntity.findById(idNo);
    }
    public Voter saveVoter( Voter voted) {
        return voterEntity.save(voted);
  
    }





}
