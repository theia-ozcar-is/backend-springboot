package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

    @JsonProperty(required = true)
    @Pattern(regexp = "^[A-Z]{4}_DAT_[^\\s]{1,}$")
    @JsonPropertyDescription("ID of the dataset. trigramme of the provider plus the number of the dataset separated with underscore ex: AMA_1")
    private String datasetId;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Metadata object representing the metadata information of the dataset")
    private Metadata metadata;

    @JsonProperty(required = true)
    private List<Observation> observations = new ArrayList<>();

    public List<Observation> getObservations() {
        return observations;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
