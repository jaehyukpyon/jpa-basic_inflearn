package both_direction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team1")
@Table(name = "Team1")
public class Team {

    @Id
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
