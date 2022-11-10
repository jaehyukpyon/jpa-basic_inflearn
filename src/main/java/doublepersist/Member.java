package doublepersist;

import javax.persistence.*;

@Entity(name = "Member_double")
@Table(name = "Member_double")
public class Member {

    @Id
    private Long memberId;

    private String memberName;

    /*@ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;*/

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /*public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/
}
