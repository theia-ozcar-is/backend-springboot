package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.association;

import java.util.List;

public class AssociationInformationDTO {
    String producerId;
    List<AssociationDTO> associations;

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public List<AssociationDTO> getAssociations() {
        return associations;
    }

    public void setAssociations(List<AssociationDTO> associations) {
        this.associations = associations;
    }
}
