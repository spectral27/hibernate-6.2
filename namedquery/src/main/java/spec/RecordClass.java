package spec;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "select_records",
        query = "select r from RecordClass r " +
                "left join fetch r.sectors"
)
@Entity
public class RecordClass {

    @Id
    @GeneratedValue
    private int id;
    private String record;

    @OneToMany(mappedBy = "record")
    private List<SectorClass> sectors = new ArrayList<>();

    public void addSector(SectorClass sector) {
        sectors.add(sector);
        sector.setRecord(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public List<SectorClass> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorClass> sectors) {
        this.sectors = sectors;
    }

}
