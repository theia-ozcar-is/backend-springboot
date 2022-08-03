package fr.theialand.insitu.dataportal.model.pivot.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import fr.theialand.insitu.dataportal.model.pivot.dataset.OnlineResource;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Person;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumProducer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Producer {

    @JsonProperty(required = true)
    @JsonPropertyDescription("4-letters ID of the Producer as defined by Theia/OZCAR comity, like CATC, CRYO...")
    private EnumProducer producerId;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Acronym of the Producer. ex AMMA-CATCH")
    private String name;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Title of the Producer")
    private String title;

    @JsonProperty(required = true)
    @JsonPropertyDescription("Short description of the Producer")
    private String description;

    @JsonPropertyDescription("Short description of the objectives of the producer")
    private String objectives;

    @JsonPropertyDescription("Short descruiption of the variables measured by the producer")
    private String measuredVariables;

    @Email
    @JsonPropertyDescription("Mail address for the Producer")
    private String email;

    @JsonProperty(required = true)
    @NotEmpty
    @JsonPropertyDescription("List of Contacts (persons) - with at least a project leader and recommended, a Data manager")
    private List<Person> contacts = new ArrayList<>();

    @JsonProperty(required = true)
    @NotEmpty
    @JsonPropertyDescription("List of funders for the Producer")
    private List<Funding> fundings = new ArrayList<>();

    @JsonPropertyDescription("Online resources of the producer")
    private OnlineResource onlineResource;

    public EnumProducer getProducerId() {
        return producerId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getMeasuredVariables() {
        return measuredVariables;
    }

    public String getEmail() {
        return email;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public List<Funding> getFundings() {
        return fundings;
    }

    public OnlineResource getOnlineResource() {
        return onlineResource;
    }
}
