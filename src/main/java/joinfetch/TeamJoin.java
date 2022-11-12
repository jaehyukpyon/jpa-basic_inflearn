package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TeamJoin")
@Table(name = "TeamJoin")
public class TeamJoin {

    @Id
    private Long teamId;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<MemberJoin> members = new ArrayList<>();

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<MemberJoin> getMembers() {
        return members;
    }

    public void setMembers(List<MemberJoin> members) {
        this.members = members;
    }
}
