package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonC {

    @Id
    private Long personCId;

    private String personCName;

    public Long getPersonCId() {
        return personCId;
    }

    public void setPersonCId(Long personCId) {
        this.personCId = personCId;
    }

    public String getPersonCName() {
        return personCName;
    }

    public void setPersonCName(String personCName) {
        this.personCName = personCName;
    }
}
