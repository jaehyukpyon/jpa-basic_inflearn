package joinfetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ChildJoin")
@Table(name = "ChildJoin")
public class ChildJoin {

    @Id
    private Long childId;

    private String childName;

    @OneToMany(mappedBy = "childJoin", fetch = FetchType.EAGER)
    private List<MemberJoin> memberJoins = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ancestor_id")
    private Ancestor ancestor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "final_id")
    private Final finalEntity;

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public List<MemberJoin> getMemberJoins() {
        return memberJoins;
    }

    public void setMemberJoins(List<MemberJoin> memberJoins) {
        this.memberJoins = memberJoins;
    }

    public Ancestor getAncestor() {
        return ancestor;
    }

    public void setAncestor(Ancestor ancestor) {
        this.ancestor = ancestor;
    }

    public Final getFinalEntity() {
        return finalEntity;
    }

    public void setFinalEntity(Final finalEntity) {
        this.finalEntity = finalEntity;
    }
}
