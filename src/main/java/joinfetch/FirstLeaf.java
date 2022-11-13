package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FirstLeaf {

    @Id
    private Long firstLeafId;

    private String firstLeafName;

    public Long getFirstLeafId() {
        return firstLeafId;
    }

    public void setFirstLeafId(Long firstLeafId) {
        this.firstLeafId = firstLeafId;
    }

    public String getFirstLeafName() {
        return firstLeafName;
    }

    public void setFirstLeafName(String firstLeafName) {
        this.firstLeafName = firstLeafName;
    }
}
