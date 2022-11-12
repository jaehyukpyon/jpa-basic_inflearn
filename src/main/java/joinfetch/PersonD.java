package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonD {

    @Id
    private Long personDId;

    private String personDName;

    public Long getPersonDId() {
        return personDId;
    }

    public void setPersonDId(Long personDId) {
        this.personDId = personDId;
    }

    public String getPersonDName() {
        return personDName;
    }

    public void setPersonDName(String personDName) {
        this.personDName = personDName;
    }
}
