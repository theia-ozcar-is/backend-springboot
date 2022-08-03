/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.List;

/**
 *
 * @author coussotc
 */
public class OnlineResource {

    @JsonPropertyDescription("Link to a data portal that enable the data download")
    private URI urlDownload;

    @JsonPropertyDescription("Link to a website with information relative to the data and the producer")
    private URI urlInfo;

    @Pattern(regexp = "\\d{2}.\\d+/.*")
    @JsonPropertyDescription("Link to a website with information relative to the data and the producer")
    private String doi;

    @JsonPropertyDescription("Various web services related to the inter operability of the data producer")
    private List<Webservice> webservices;

    public URI getUrlDownload() {
        return urlDownload;
    }

    public URI getUrlInfo() {
        return urlInfo;
    }

    public String getDoi() {
        return doi;
    }

    public List<Webservice> getWebservices() {
        return webservices;
    }
}
