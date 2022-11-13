package joinfetch_2;

import javax.persistence.*;

@Entity
@Table
public class EntityA {

    @Id
    private Long entityAId;

    private String entityAName;

    @ManyToOne(fetch = FetchType.LAZY)
    private EntityB entityB;

    public Long getEntityAId() {
        return entityAId;
    }

    public void setEntityAId(Long entityAId) {
        this.entityAId = entityAId;
    }

    public String getEntityAName() {
        return entityAName;
    }

    public void setEntityAName(String entityAName) {
        this.entityAName = entityAName;
    }

    public EntityB getEntityB() {
        return entityB;
    }

    public void setEntityB(EntityB entityB) {
        this.entityB = entityB;
    }
}
