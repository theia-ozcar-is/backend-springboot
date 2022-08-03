package fr.theialand.insitu.dataportal.etl.model;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GemetThesaurus extends Thesaurus {

    private static final String CATALOG_NAME = "GEMET - INSPIRE themes, version 1.0";
    private static final URI     CATALOG_URL = URI.create("http://www.eionet.europa.eu/gemet/inspire_themes") ;
    private static final Date   CATALOG_DATE = new GregorianCalendar(2008, Calendar.JUNE, 1).getTime();

    public GemetThesaurus() {
        super(CATALOG_NAME,CATALOG_URL,CATALOG_DATE);
    }


}
