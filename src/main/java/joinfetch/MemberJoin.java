package joinfetch;

import javax.persistence.*;

@Entity(name = "MemberJoin")
@Table(name = "MemberJoin")
public class MemberJoin {

    @Id
    private Long memberId;

    private String memberName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamJoin team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id")
    private ChildJoin childJoin;

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

    public TeamJoin getTeam() {
        return team;
    }

    public void setTeam(TeamJoin team) {
        this.team = team;
    }

    public ChildJoin getChildJoin() {
        return childJoin;
    }

    public void setChildJoin(ChildJoin childJoin) {
        this.childJoin = childJoin;
    }
}
