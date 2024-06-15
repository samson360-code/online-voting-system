package votingsystem.voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepositery extends JpaRepository <Voter,String>{
} 
