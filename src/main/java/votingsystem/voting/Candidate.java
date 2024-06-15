package votingsystem.voting;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Candidate {
    @Id
    private String candId;
    private String fname;
    private String lname;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(columnDefinition = "TEXT")
    private String quote;
    private String image;
    private Integer votedBy = 0;

    public String getcandId() {
        return candId;
    }

    public void setcandId(String candId) {
        this.candId = candId;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getlname() {
        return lname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }

    public String getbio() {
        return bio;
    }

    public void setbio(String bio) {
        this.bio = bio;
    }

    public String getquote() {
        return quote;
    }

    public void setquote(String quote) {
        this.quote = quote;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        String directoryPath = "/image/";
        this.image = directoryPath + image;
    }

    public Integer getvotedBy() {
        return votedBy;
    }

    public void setvotedBy() {
        this.votedBy++;
    }

}
