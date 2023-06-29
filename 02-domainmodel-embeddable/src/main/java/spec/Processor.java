package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Processor {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private ProcessorCodename processorCodename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProcessorCodename getProcessorCodename() {
        return processorCodename;
    }

    public void setProcessorCodename(ProcessorCodename processorCodename) {
        this.processorCodename = processorCodename;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processorCodename=" + processorCodename +
                '}';
    }

}
