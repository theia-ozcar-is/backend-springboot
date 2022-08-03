package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "variableCategories")
public class TheiaCategory {

    @Id
    private String id;
    private String uri;
    private List<String> broaders;
    private List<String> narrowers;
    private List<I18n> prefLabel;
    private List<I18n> simplifiedLabel;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<I18n> getSimplifiedLabel() {
        return simplifiedLabel;
    }

    public void setSimplifiedLabel(List<I18n> simplifiedLabel) {
        this.simplifiedLabel = simplifiedLabel;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getBroaders() {
        return broaders;
    }

    public void setBroaders(List<String> broaders) {
        this.broaders = broaders;
    }

    public List<String> getNarrowers() {
        return narrowers;
    }

    public void setNarrowers(List<String> narrowers) {
        this.narrowers = narrowers;
    }

    public List<I18n> getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(List<I18n>  prefLabel) {
        this.prefLabel = prefLabel;
    }


}
