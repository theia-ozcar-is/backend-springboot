package fr.theialand.insitu.dataportal.api.cgmwgeology;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = GeologyWMSClientImpl.class)
class GeologyWMSClientImplTest {

    @Autowired
    GeologyWMSClientImpl geologyWMSClient;


    @Test
    void getGeology() {
        List<Double> location = new ArrayList<>();
        location.add(55.48350976034829);
        location.add(-21.177602417538882);
//        System.out.println(this.geologyWMSClient);
        //this.geologyWMSClient.getGeology(location);

    }
}