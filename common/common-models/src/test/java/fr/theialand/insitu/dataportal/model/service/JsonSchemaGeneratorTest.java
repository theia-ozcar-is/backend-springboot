package fr.theialand.insitu.dataportal.model.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.pivot.dataset.*;
import fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.model.pivot.observation.FeatureOfInterest;
import fr.theialand.insitu.dataportal.model.pivot.observation.Observation;
import fr.theialand.insitu.dataportal.model.pivot.producer.Funding;
import fr.theialand.insitu.dataportal.model.pivot.producer.Producer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class JsonSchemaGeneratorTest {

    @Autowired
    ApplicationContext context;

    JsonSchemaGenerator schemaGen;

    @BeforeAll
    public void init() {
        schemaGen = (JsonSchemaGenerator) context.getBean("jsonSchemaGenerator","DRAFT_2019_09", context );
    }

    @Test
    @DisplayName("Ensure we dont generate I18n, Contact, and we have required fields")
    void generatedJsonShouldContainRequiredFieldNoI18nAndNoContact() throws  JsonProcessingException {

        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(TestClass.class);

        assertThat(jsonSchemaAsString)
                .isNotNull()
                .contains("required")
//                .contains("i18nString\":{\"type\":\"string")
                .contains("anyOf");//:[{\"$ref\":\"#/$defs/Person") ;//.. , Organisation...
    }

    @ParameterizedTest
    @ValueSource(classes = { Funding.class, FeatureOfInterest.class, Person.class, Organisation.class, Producer.class, Observation.class, Dataset.class, Metadata.class } )
    <T> void generatedJsonSchemaShouldNotContainI18nDefinition( Class<T> classToSchematize ) throws JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(classToSchematize);

        assertThat(jsonSchemaAsString)
                .doesNotContain("I18n") ;
    }

    @ParameterizedTest
    @ValueSource(classes = { Funding.class, FeatureOfInterest.class, Person.class, Organisation.class, Producer.class, Observation.class, Dataset.class, Metadata.class } )
    <T> void generatedJsonSchemaShouldNotContainContactAbstract(Class<T> classToSchematize ) throws JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(Producer.class);

        assertThat(jsonSchemaAsString)
                .doesNotContain("\"Contact\"") ;
    }

    @Test
    void generatedPublicProducerJsonSchemaShouldContainPerson() throws  JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(Producer.class);

        assertThat(jsonSchemaAsString)
                .contains("Person")
                .doesNotContain("Contact\""); // not to be confused with EnumContactRoles
    }

    @Test
    void generatedSchemaShouldIncludeRegex() throws  JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(TestClass.class);

        assertThat(jsonSchemaAsString)
                .contains("pattern\" : \"[A-Z]") ;
    }

    @Test
    void generatedSchemaShouldIncludeEmailFormat() throws  JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(TestClass.class);

        assertThat(jsonSchemaAsString)
                .contains("\"format\" : \"email") ;
    }

    @Test
    void generatedSchemaOfPivotShouldBeDraft2019() throws  JsonProcessingException {
        String jsonSchemaAsString = schemaGen.getJsonSchemaPrettyPrint(Pivot.class);
        System.out.println(jsonSchemaAsString);

        assertThat(jsonSchemaAsString).contains("json-schema.org/draft/2019-09/schema");
    }

    static class TestClass {
        @JsonProperty(required = true)
        private String i18nString;

        @Email
        private String fakeEmail;

        private String fakeUrl;

        @Pattern(regexp = "[A-Z]{4}$")
        private String prodId;

        private Contact contact;
        private List<Contact> contacts;

        private EnumContactPersonRoles roles;

        public String getI18nString() {
            return i18nString;
        }

        public void setI18nString(String i18nString) {
            this.i18nString = i18nString;
        }

        public String getFakeEmail() {
            return fakeEmail;
        }

        public void setFakeEmail(String fakeEmail) {
            this.fakeEmail = fakeEmail;
        }

        public String getProdId() {
            return prodId;
        }

        public void setProdId(String prodId) {
            this.prodId = prodId;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public List<Contact> getContacts() {
            return contacts;
        }

        public void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
        }

        public EnumContactPersonRoles getRoles() {
            return roles;
        }

        public void setRoles(EnumContactPersonRoles roles) {
            this.roles = roles;
        }

        public String getFakeUrl() {
            return fakeUrl;
        }

        public void setFakeUrl(String fakeUrl) {
            this.fakeUrl = fakeUrl;
        }
    }
}
