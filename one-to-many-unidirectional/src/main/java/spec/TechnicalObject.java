package spec;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TechnicalObject {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany
    private List<TechnicalVersion> technicalVersions = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("%d %s", id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TechnicalVersion> getTechnicalVersions() {
        return technicalVersions;
    }

    public void setTechnicalVersions(List<TechnicalVersion> technicalVersions) {
        this.technicalVersions = technicalVersions;
    }

}
