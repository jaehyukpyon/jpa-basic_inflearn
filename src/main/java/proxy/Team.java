package proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team_p")
@Table(name = "Team_p")
public class Team {

    @Id
    @GeneratedValue
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
