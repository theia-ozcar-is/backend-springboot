package fr.theialand.insitu.dataportal.etl.model;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TheiaVariablesThesaurus extends Thesaurus {

    private static final String CATALOG_NAME = "Theia/OZCAR thesaurus";
    private static final URI     CATALOG_URL = URI.create("https://w3id.org/ozcar-theia") ;
    private static final Date   CATALOG_DATE = new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime();

    public TheiaVariablesThesaurus() {
        super(CATALOG_NAME, CATALOG_URL, CATALOG_DATE);
    }

}
