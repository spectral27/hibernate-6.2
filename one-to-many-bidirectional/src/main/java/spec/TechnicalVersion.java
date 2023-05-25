package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

@Entity
public class TechnicalVersion {

    @Id
    @GeneratedValue
    private Integer id;
    private String version;
    private Date releaseDate;

    @ManyToOne
    private TechnicalObject object;

    public TechnicalVersion() {

    }

    public TechnicalVersion(String version, Date releaseDate) {
        this.version = version;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %s %s", id, version, releaseDate));
        if (object != null) {
            sb.append(String.format("\n%d %s", object.getId(), object.getName()));
        }
        return sb.toString();
    }

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

    public TechnicalObject getObject() {
        return object;
    }

    public void setObject(TechnicalObject object) {
        this.object = object;
    }

}
