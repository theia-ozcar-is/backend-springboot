package fr.theialand.insitu.dataportal.etl.model;

import org.springframework.lang.NonNull;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TheiaKeywordThesaurus extends Thesaurus {

    private static final Date   CATALOG_DATE = new GregorianCalendar(2020, Calendar.DECEMBER, 1).getTime();

    public TheiaKeywordThesaurus(@NonNull String catalogName) {
        super(
                catalogName,
                URI.create("https://theia-ozcar.gricad-pages.univ-grenoble-alpes.fr/doc-producer/producer-documentation.html#thesaurus-theia-ozcar-categories-et-noms-de-variables"),
                CATALOG_DATE);
    }
}
