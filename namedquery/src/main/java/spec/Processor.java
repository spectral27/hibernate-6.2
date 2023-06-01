package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@NamedQuery(
        name = "select_processors",
        query = "select p from Processor p"
)
@Entity
public class Processor {

    @Id
    @GeneratedValue
    private int id;
    private String model;

    public Processor() {

    }

    public Processor(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
