package joinfetch;

import javax.persistence.*;

@Entity
@Table
public class Ancestor {

    @Id
    private Long ancestorId;

    private String ancestorName;

    @ManyToOne(fetch = FetchType.EAGER)
    private FirstLeaf firstLeaf;

    @ManyToOne(fetch = FetchType.EAGER)
    private SecondLeaf secondLeaf;

    public Long getAncestorId() {
        return ancestorId;
    }

    public void setAncestorId(Long ancestorId) {
        this.ancestorId = ancestorId;
    }

    public String getAncestorName() {
        return ancestorName;
    }

    public void setAncestorName(String ancestorName) {
        this.ancestorName = ancestorName;
    }

    public FirstLeaf getFirstLeaf() {
        return firstLeaf;
    }

    public void setFirstLeaf(FirstLeaf firstLeaf) {
        this.firstLeaf = firstLeaf;
    }

    public SecondLeaf getSecondLeaf() {
        return secondLeaf;
    }

    public void setSecondLeaf(SecondLeaf secondLeaf) {
        this.secondLeaf = secondLeaf;
    }
}
