package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Processor {

    @Id
    @GeneratedValue
    private Integer id;
    private String model;
    private Integer cores;
    private Integer threads;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    @Override
    public String toString() {
        return String.format("%s: %d ", "Id", id) +
                String.format("%s: %s ", "Model", model) +
                String.format("%s: %d ", "Cores", cores) +
                String.format("%s: %d", "Threads", threads);
    }

}
