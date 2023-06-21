package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SectorClass {

    @Id
    @GeneratedValue
    private int id;
    private String sector;

    @ManyToOne
    private RecordClass record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public RecordClass getRecord() {
        return record;
    }

    public void setRecord(RecordClass record) {
        this.record = record;
    }

}
