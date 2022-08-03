/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author coussotc
 */
public class TheiaVariable {
    private String uri;
    private List<I18n> prefLabel;
    private List<String> broaderCategories;
    private List<I18n> simplifiedLabel;

    public List<I18n> getSimplifiedLabel() {
        return simplifiedLabel;
    }

    public void setSimplifiedLabel(List<I18n> simplifiedLabel) {
        this.simplifiedLabel = simplifiedLabel;
    }

    public List<String> getBroaderCategories() {
        return broaderCategories;
    }

    public void setBroaderCategories(List<String> broaderCategories) {
        this.broaderCategories = broaderCategories;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<I18n> getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(List<I18n> prefLabel) {
        this.prefLabel = prefLabel;
    }

    @Override
    public int hashCode() {
        if (this.uri == null){
            return Objects.hashCode(
                    this.simplifiedLabel.stream().filter(label -> label.getLang().equals("en")).findFirst().get().getText()
            );
        } else {
            return Objects.hashCode(this.uri);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TheiaVariable other = (TheiaVariable) obj;
        if (!Objects.equals(this.uri, other.uri)) {
            return false;
        }
        return true;
    }  
}
