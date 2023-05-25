package spec;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Processor {

    @Id
    private Integer id;
    private String model;
    private Integer cores;
    private Integer threads;
    private String maxFrequency;
    private String cache;
    private String maxRam;
    private String ramType;
    private Integer ramChannels;

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

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(String maxRam) {
        this.maxRam = maxRam;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public Integer getRamChannels() {
        return ramChannels;
    }

    public void setRamChannels(Integer ramChannels) {
        this.ramChannels = ramChannels;
    }

}
