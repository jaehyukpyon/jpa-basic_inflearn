package joinfetch;

import javax.persistence.*;

@Entity
public class PersonA {

    @Id
    private Long personAId;

    private String personAName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personb_id")
    private PersonB personB;

    public Long getPersonAId() {
        return personAId;
    }

    public void setPersonAId(Long personAId) {
        this.personAId = personAId;
    }

    public String getPersonAName() {
        return personAName;
    }

    public void setPersonAName(String personAName) {
        this.personAName = personAName;
    }

    public PersonB getPersonB() {
        return personB;
    }

    public void setPersonB(PersonB personB) {
        this.personB = personB;
    }
}
