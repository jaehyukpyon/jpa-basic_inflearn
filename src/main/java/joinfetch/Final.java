package joinfetch;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Final {

    @Id
    private Long finalId;

    private String finalName;

    public Long getFinalId() {
        return finalId;
    }

    public void setFinalId(Long finalId) {
        this.finalId = finalId;
    }

    public String getFinalName() {
        return finalName;
    }

    public void setFinalName(String finalName) {
        this.finalName = finalName;
    }
}
