package votingsystem.voting;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Random;

@Entity
public class Voter {
    @Id
    private String idNo;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String password;
    private String role="voter";
    private boolean isVoted=false;
    private String voterKey;

    public String getVoterKey() {
        return voterKey;
    }

    public void voteKeyGenretor() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@!@$%&";
        StringBuilder voteKey = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            voteKey.append(randomChar);
        }
        this.voterKey = voteKey.toString();
    }

    public void setidNo(String idNo) {
        this.idNo = idNo;
    }

    public String getidNo() {
        return idNo;
    }

    public void setfName(String fname) {
        this.fName = fname;
    }

    public String getfName() {
        return fName;
    }

    public void setlName(String lname) {
        this.lName = lname;
    }

    public String getlName() {
        return lName;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getemail() {
        return email;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getphone() {
        return phone;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getpassword() {
        return password;
    }
    
    public void setrole(String role) {
        this.role = role;
    }

    public String getrole() {
        return role;
    }

    public void setisVoted(boolean isVoted) {
        this.isVoted = isVoted;
    }

    public boolean getisVoted() {
        return isVoted;
    }
}