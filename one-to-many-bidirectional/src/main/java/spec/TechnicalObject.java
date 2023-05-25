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

    @OneToMany(mappedBy = "object")
    private List<TechnicalVersion> technicalVersions = new ArrayList<>();

    public TechnicalObject() {

    }

    public TechnicalObject(String name) {
        this.name = name;
    }

    public void addVersion(TechnicalVersion version) {
        technicalVersions.add(version);
        version.setObject(this);
    }

    public void removeVersion(TechnicalVersion version) {
        technicalVersions.remove(version);
        version.setObject(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %s", id, name));
        if (technicalVersions.size() > 0) {
            for (TechnicalVersion version : technicalVersions) {
                sb.append(String.format("\n%d %s %s", version.getId(), version.getVersion(), version.getReleaseDate()));
            }
        }
        return sb.toString();
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
