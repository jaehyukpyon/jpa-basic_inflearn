package joinfetch_2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class EntityB {

    @Id
    private Long entityBId;

    private String entityBName;

    public Long getEntityBId() {
        return entityBId;
    }

    public void setEntityBId(Long entityBId) {
        this.entityBId = entityBId;
    }

    public String getEntityBName() {
        return entityBName;
    }

    public void setEntityBName(String entityBName) {
        this.entityBName = entityBName;
    }
}
