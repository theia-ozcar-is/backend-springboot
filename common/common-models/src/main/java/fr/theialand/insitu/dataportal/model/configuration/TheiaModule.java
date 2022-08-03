package fr.theialand.insitu.dataportal.model.configuration;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mongodb.client.model.geojson.Geometry;
import fr.theialand.insitu.dataportal.model.configuration.deserializers.MongoGeometryDeserializer;

public class TheiaModule extends SimpleModule {
    TheiaModule() {
        super.addDeserializer(Geometry.class,new MongoGeometryDeserializer());
//        super.addDeserializer(GeoJson.class,new SpringDataMongoGeoJsonDeserializer());
//        super.addDeserializer(Contact.class,new ContactsDeserializer());
//        super.addDeserializer(Sensor.class, new SensorCustomDeserializer());
//        super.addDeserializer(Properties.class, new FeatureOfInterestPropertiesDeserializer());
//        super.addDeserializer(SamplingFeature.class,new SamplingFeatureDeserializer());
//        // Need a big refacto in importMetadata and use objects instead of JsonNode, before we can include this deserialisetr
//        super.addDeserializer(I18nList.class, new I18nListDeserializer());

    }
}
