package cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PARENT_CASCADE")
@Table(name = "PARENT_CASCADE")
public class Parent {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
