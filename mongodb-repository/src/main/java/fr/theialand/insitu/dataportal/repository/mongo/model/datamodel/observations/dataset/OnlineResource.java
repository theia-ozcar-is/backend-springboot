/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;

import java.util.List;

/**
 *
 * @author coussotc
 */
public class OnlineResource {

    private List<I18n> urlDownload;
    private List<I18n> urlInfo;
    private String doi;
    private List<Webservice> webservices;

    public List<I18n> getUrlDownload() {
        return urlDownload;
    }

    public void setUrlDownload(List<I18n> urlDownload) {
        this.urlDownload = urlDownload;
    }

    public List<I18n> getUrlInfo() {
        return urlInfo;
    }

    public void setUrlInfo(List<I18n> urlInfo) {
        this.urlInfo = urlInfo;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public List<Webservice> getWebservices() {
        return webservices;
    }

    public void setWebservices(List<Webservice> webservices) {
        this.webservices = webservices;
    }
    
    

}
