package fr.theialand.insitu.dataportal.api.geonetwork;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;


@ComponentScan
@Configuration
@EnableAutoConfiguration  // so the custom HttpConverter is registered with Spring RestTemplate
public class GeonetworkConfig {

    /**
     * Register a very simple application/xml TO Object(String in fact) Converter
     * @return a {@link HttpMessageConverter} that is automagically registered by Boot
     */
    @Bean
    private static SimpleXmlStringHttpMessageConverter createSimpleXmlToStringConverter() {
        return new SimpleXmlStringHttpMessageConverter();
    }

    /**
     * To be able to read an XML into a String without the need for Object Mapping layer
     */
    public static class SimpleXmlStringHttpMessageConverter extends StringHttpMessageConverter{

        public SimpleXmlStringHttpMessageConverter(){
            super(StandardCharsets.UTF_8); // ISO 8859 by default ...
        }

        // for the getRecordAsXMl
        @Override
        public boolean canRead(@NonNull Class<?> clazz, MediaType mediaType) {
            return MediaType.APPLICATION_XML.equals(mediaType);
        }

        // for sending the string as XML, we use this message converter because it is in utf8
        @Override
        protected boolean canWrite(MediaType mediaType) {
            return MediaType.APPLICATION_XML.equals(mediaType);
        }
    }
}
