package spec;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProcessorCodename {

    private String codename;

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    @Override
    public String toString() {
        return "ProcessorCodename{" +
                "codename='" + codename + '\'' +
                '}';
    }

}
