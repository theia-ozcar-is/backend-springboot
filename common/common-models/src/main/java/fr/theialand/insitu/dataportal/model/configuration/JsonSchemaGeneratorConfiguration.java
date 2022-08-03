package fr.theialand.insitu.dataportal.model.configuration;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationOption;
import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.pivot.dataset.Organisation;
import fr.theialand.insitu.dataportal.model.pivot.producer.Funding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@ComponentScan("fr.theialand.insitu.dataportal.model")
public class JsonSchemaGeneratorConfiguration {

    @Value("${json.schema.url}")
    private String jsonSchemaUrl;

    private static String PIVOT_SCHEMA   = "pivotSchema.json";
    private static String GEOJSON_SCHEMA = "geoJsonSchema.json";

    private static final Logger LOG = LoggerFactory.getLogger(JsonSchemaGeneratorConfiguration.class);

    @Bean
    @Scope("prototype")
    SchemaGenerator prepareSchemaGenerator(String version) {
        SchemaVersion schemaVersion;
        if (version == "DRAFT_7") {
            schemaVersion = SchemaVersion.DRAFT_7;
            PIVOT_SCHEMA = "pivotSchemaDraft7.json";
            GEOJSON_SCHEMA = "geoJsonSchemaDraft7.json";
        } else {
            schemaVersion = SchemaVersion.DRAFT_2019_09;
        }
        // doc:
        //https://victools.github.io/jsonschema-generator/

        SchemaGeneratorConfigBuilder schemaConfigBuilder = new SchemaGeneratorConfigBuilder(schemaVersion , OptionPreset.PLAIN_JSON); // PLAIN_JSON is a preset of Options, careful when changing this !

        schemaConfigBuilder
                .with(new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_ORDER, JacksonOption.RESPECT_JSONPROPERTY_REQUIRED)) // since 4.18, respecting @JsonProperty(req=true) is respected via this opt-in
                .with(new JavaxValidationModule(JavaxValidationOption.INCLUDE_PATTERN_EXPRESSIONS))
                .with(Option.DEFINITIONS_FOR_ALL_OBJECTS) // if enabled, it makes a lot of "allOf..." quite unreadable. if enabled, it makes enums as objects
                .with(Option.FLATTENED_ENUMS_FROM_TOSTRING) // so enum are not Objects, and their tostring is used
                .with(Option.EXTRA_OPEN_API_FORMAT_VALUES)  // so "format" is generated for known props: Date => "date-time", URI => "uri" , integer => "int32". Remove the need for .withStringFormatResolver(..)
                // for now, we accept empty string, null, empty field, and treat all of them the same.
                // our contract is not clear, this option might come in handy, especially when using the recursive func "removeemptysandnull()" which add another level of unclear.
                // .with(Option.NULLABLE_FIELDS_BY_DEFAULT)

                .with(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT); // to reject unknown props

        schemaConfigBuilder.forTypesInGeneral()
                // Hard code the ID. hopefully we would only have a single schema.
                .withIdResolver( scope -> {
                    if (scope.getType().getErasedType() == Pivot.class) {
                        LOG.info("Initializing Schema Generator with URL for pivot schema : {}",this.jsonSchemaUrl+PIVOT_SCHEMA);
                        return this.jsonSchemaUrl+PIVOT_SCHEMA;
                    } else {
                        return null;
                    }
                })

                // trying to get an nice anyOf for geojson...
                // https://stackoverflow.com/questions/62458285/java-multi-schema-generator-using-annotations
                .withCustomDefinitionProvider((declaredType, context) -> {
                    if (declaredType.getErasedType() == Geometry.class) {
                        LOG.info("Initializing Schema Generator with URL for geoJson schema : {}",this.jsonSchemaUrl+GEOJSON_SCHEMA);
                        ObjectNode customSubschema = context.getGeneratorConfig().createObjectNode()
                                .put(SchemaKeyword.TAG_REF.forVersion(schemaVersion), this.jsonSchemaUrl+GEOJSON_SCHEMA );
                        // AttributeInclusion.NO means that it WONT add "additionalProperties:false"
                        return new CustomPropertyDefinition(customSubschema, CustomDefinition.AttributeInclusion.NO);
                    }
                    return null; // "next" in victools language
                })

                // trying to get an nice anyOf for Organisation with acronym or Scanr or Name...
                .withSubtypeResolver((declaredType, context) -> {
                    if (declaredType.getErasedType() == Organisation.class) {
                        TypeContext typeContext = context.getTypeContext();
                        return Arrays.asList(
                                typeContext.resolveSubtype(declaredType, Organisation.OrganisationWithName.class),
                                typeContext.resolveSubtype(declaredType, Organisation.OrganisationWithAcronym.class),
                                typeContext.resolveSubtype(declaredType, Organisation.OrganisationWithScanRiD.class)
                        );
                    }
                    return null; // "next" in victools language
                })

                // trying to get an nice anyOf for Funding with acronym or Scanr or Name...
                .withSubtypeResolver((declaredType, context) -> {
                    if (declaredType.getErasedType() == Funding.class) {
                        TypeContext typeContext = context.getTypeContext();
                        return Arrays.asList(
                                typeContext.resolveSubtype(declaredType, Funding.FundingWithName.class),
                                typeContext.resolveSubtype(declaredType, Funding.FundingWithAcronym.class),
                                typeContext.resolveSubtype(declaredType, Funding.FundingWithScanRiD.class)
                        );
                    }
                    return null; // "next" in victools language
                })   ;

        // some global configuration depending on the Types
        schemaConfigBuilder.forFields()
                // all the json array should have a minItem of 1.
                // the same as having @NotEmpty everywhere
                .withArrayMinItemsResolver(field -> field.getType().isInstanceOf(Collection.class) ? 1 : null)
                // all the strings should have a minlength of 1.
                // the same as having @NotBlank everywhere
                .withStringMinLengthResolver( field -> field.getType().isInstanceOf(String.class) ? 1 : null);
        
        return new SchemaGenerator(schemaConfigBuilder.build());
    }
}
