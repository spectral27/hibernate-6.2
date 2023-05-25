package spec;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class TechnicalVersion {

    @Id
    @GeneratedValue
    private Integer id;
    private String version;
    private Date releaseDate;

    @Override
    public String toString() {
        return String.format("TechnicalVersion: %d %s %s", id, version, releaseDate);
    }

    @ManyToOne
    private TechnicalObject technicalObject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public TechnicalObject getTechnicalObject() {
        return technicalObject;
    }

    public void setTechnicalObject(TechnicalObject technicalObject) {
        this.technicalObject = technicalObject;
    }

}
