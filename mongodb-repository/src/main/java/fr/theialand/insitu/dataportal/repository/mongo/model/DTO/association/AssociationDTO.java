package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;

import java.util.List;

public class AssociationDTO {
    //OperationEnum operation;
    List<I18n> prefLabel;
    String uri;
//    String uriToBeUpdated;
    List<String> broaderCategories;
    List<I18n> simplifiedLabel;
    ProducerVariable producerVariable;

    public enum OperationEnum {
        DELETE,
        UPDATE,
        CREATE
    }

    public class ProducerVariable {
        List<I18n> name;
        int oldIndex;
        List<String> theiaCategories;
        TheiaVariable theiaVariable;
        List<I18n> unit;

        public List<I18n> getName() {
            return name;
        }

        public void setName(List<I18n> name) {
            this.name = name;
        }

        public int getOldIndex() {
            return oldIndex;
        }

        public void setOldIndex(int oldIndex) {
            this.oldIndex = oldIndex;
        }

        public List<String> getTheiaCategories() {
            return theiaCategories;
        }

        public void setTheiaCategories(List<String> theiaCategories) {
            this.theiaCategories = theiaCategories;
        }

        public List<I18n> getUnit() {
            return unit;
        }

        public void setUnit(List<I18n> unit) {
            this.unit = unit;
        }

        public TheiaVariable getTheiaVariable() {
            return theiaVariable;
        }

        public void setTheiaVariable(TheiaVariable theiaVariable) {
            this.theiaVariable = theiaVariable;
        }
    }

    public List<I18n> getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(List<I18n> prefLabel) {
        this.prefLabel = prefLabel;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public List<String> getBroaderCategories() {
        return broaderCategories;
    }

    public void setBroaderCategories(List<String> broaderCategories) {
        this.broaderCategories = broaderCategories;
    }

    public List<I18n> getSimplifiedLabel() {
        return simplifiedLabel;
    }

    public void setSimplifiedLabel(List<I18n> simplifiedLabel) {
        this.simplifiedLabel = simplifiedLabel;
    }

    public ProducerVariable getProducerVariable() {
        return producerVariable;
    }

    public void setProducerVariable(ProducerVariable producerVariable) {
        this.producerVariable = producerVariable;
    }
 }

