package fr.theialand.insitu.dataportal.etl.model;

import java.net.URI;
import java.util.Date;

/**
 * class to be extended by all Thesaurus.
 * see @{@link GemetThesaurus} and @{@link TheiaVariablesThesaurus} and @{@link TheiaKeywordThesaurus}
 * this structure get some code out of the business layer.
 */
public abstract class Thesaurus {

    private final String name;
    private final URI url;
    private final Date date;

    protected Thesaurus(String name, URI url, Date date) {
        this.name=name;
        this.url = url;
        this.date = date;
    }

    public String getCatalogName() {
        return name;
    }

    public URI getUrl() {
        return url;
    }

    public Date getCatalogDate() {
        return date;
    }

}
