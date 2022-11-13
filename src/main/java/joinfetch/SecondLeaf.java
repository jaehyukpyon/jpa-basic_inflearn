package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecondLeaf {

    @Id
    private Long secondLeafId;

    private String secondLeafName;

    public Long getSecondLeafId() {
        return secondLeafId;
    }

    public void setSecondLeafId(Long secondLeafId) {
        this.secondLeafId = secondLeafId;
    }

    public String getSecondLeafName() {
        return secondLeafName;
    }

    public void setSecondLeafName(String secondLeafName) {
        this.secondLeafName = secondLeafName;
    }
}
