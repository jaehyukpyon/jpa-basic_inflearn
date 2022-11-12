package joinfetch;

import javax.persistence.*;

@Entity
public class PersonB {

    @Id
    private Long personBId;

    private String personBName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personc_id")
    private PersonC personC;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "persond_id")
    private PersonD personD;

    public Long getPersonBId() {
        return personBId;
    }

    public void setPersonBId(Long personBId) {
        this.personBId = personBId;
    }

    public String getPersonBName() {
        return personBName;
    }

    public void setPersonBName(String personBName) {
        this.personBName = personBName;
    }

    public PersonC getPersonC() {
        return personC;
    }

    public void setPersonC(PersonC personC) {
        this.personC = personC;
    }

    public PersonD getPersonD() {
        return personD;
    }

    public void setPersonD(PersonD personD) {
        this.personD = personD;
    }
}
