package fr.theialand.insitu.dataportal.repository.mongo.model.view;

/**
 *
 * @author coussotc
 */
public class ProducerStat {

    private String name;
    private int associated;
    private int total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAssociated() {
        return associated;
    }

    public void setAssociated(int associated) {
        this.associated = associated;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
