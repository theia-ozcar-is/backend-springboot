package fr.theialand.insitu.dataportal.api.restcountries.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Written by hand, as RestCountries does not provide a swagger..
 * with only the fields relevant to us
 */
public class RestCountry implements Serializable {

    private String name;

    private String alpha2Code;

    private Map<String, String> translations;

    public String getName() {
        return name;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }
}
