package fr.theialand.insitu.dataportal.api.cgmwgeology;

import fr.theialand.insitu.dataportal.api.cgmwgeology.model.CGMWGeology;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

public interface GeologyWMSClient {
    CGMWGeology getGeology(List<Double> location) ;
}
