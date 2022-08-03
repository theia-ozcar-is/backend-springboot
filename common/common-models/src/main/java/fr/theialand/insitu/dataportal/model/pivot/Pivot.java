package fr.theialand.insitu.dataportal.model.pivot;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Dataset;
import fr.theialand.insitu.dataportal.model.pivot.producer.Producer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@JsonClassDescription("Main definition file for a producer data")
public class Pivot {
    @JsonProperty(required = true)
    private Producer producer;

    @JsonProperty(required = true)
    private List<Dataset> datasets;

    @JsonProperty(required = true)
    @Pattern(regexp = "\\d+.\\d+$")
    private String version;

    public Producer getProducer() {
        return producer;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }

    public String getVersion() {
        return version;
    }
}
