package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClientImpl;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.TheiaCategoriesException;
import fr.theialand.insitu.dataportal.model.configuration.JsonSchemaGeneratorConfiguration;
import fr.theialand.insitu.dataportal.model.configuration.ObjectMapperConfiguration;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.ObservedProperty;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootTest(classes = {
        JsonFileValidationServiceImpl.class,
        JsonSchemaGeneratorConfiguration.class,
        ObjectMapperConfiguration.class,
        JsonSchemaValidatorImpl.class,
        InputValidationServiceImpl.class,
        DataFileValidationServiceImpl.class

})
class InputValidationServiceImplTest {


    @SpyBean
    private RDFQueryClientImpl rdfDaoSpy;

    @Bean
    public ObjectMapper myObjectMapper() {
        return new ObjectMapper();
    }

    @Autowired
    InputValidationServiceImpl inputValidationService;

    @Test
    void validateTheiaCategoriesOfJson1() throws TheiaCategoriesException {
        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setTheiaCategories(List.of("https://w3id.org/ozcar-theia/c_d9824eec"));
        ObservedProperty observedPropertyValidated = inputValidationService.validateTheiaCategoriesOfJson(observedProperty);
        Assert.assertEquals(observedPropertyValidated.getTheiaCategories().get(0),observedProperty.getTheiaCategories().get(0));
    }

    @Test
    void validateTheiaCategoriesOfJson2() throws TheiaCategoriesException {
        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setTheiaCategories(List.of("https://w3id.org/ozcar-theia/atmosphere"));
        ObservedProperty observedPropertyValidated = inputValidationService.validateTheiaCategoriesOfJson(observedProperty);
        Assert.assertEquals(observedPropertyValidated.getTheiaCategories().get(0),"https://w3id.org/ozcar-theia/c_d9824eec");
    }

    @Test
    void validateTheiaCategoriesOfJson2bis() throws TheiaCategoriesException {
        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setTheiaCategories(List.of("https://w3id.org/ozcar-theia/erosionSedimentation"));
        ObservedProperty observedPropertyValidated = inputValidationService.validateTheiaCategoriesOfJson(observedProperty);
        Assert.assertEquals(observedPropertyValidated.getTheiaCategories().get(0),"https://w3id.org/ozcar-theia/c_0f1ab34e");
    }

    @Test
    void validateTheiaCategoriesOfJson3() throws TheiaCategoriesException {
        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setTheiaCategories(List.of("https://w3id.org/ozcar-theia/c_c14e4576"));
        ObservedProperty observedPropertyValidated = inputValidationService.validateTheiaCategoriesOfJson(observedProperty);
        Assert.assertEquals(observedPropertyValidated.getTheiaCategories().get(0),"https://w3id.org/ozcar-theia/c_d3629e44");
        Assert.assertEquals(observedPropertyValidated.getKeywords().get(0).getUri(),"https://w3id.org/ozcar-theia/c_c14e4576");
        Assert.assertEquals(observedPropertyValidated.getKeywords().get(0).getKeyword().get(0).getText(),"Air pressure at 5 meters height");
    }
}