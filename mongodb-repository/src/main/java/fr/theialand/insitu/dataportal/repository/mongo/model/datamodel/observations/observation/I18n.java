/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import java.util.Objects;

/**
 *
 * @author coussotc
 */
public class I18n {

    String lang;
    private String text;

    public I18n(String lang, String text) {
        this.lang = lang;
        this.text = text;
    }

    public I18n() {
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toJson() {
        return "{\"lang\" : \""+this.getLang()+"\", \"text\" : \"" + this.getText()+"\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof I18n)) return false;
        I18n i18n = (I18n) o;
        return Objects.equals(lang, i18n.lang) && Objects.equals(text, i18n.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, text);
    }

    @Override
    public String toString() {
        return this.getText()+ "@" + this.getLang();
    }
}
