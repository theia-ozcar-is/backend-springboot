/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.dataset;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 *
 * @author coussotc
 */
public class TemporalExtent {

    @JsonProperty(required = true)
    private Date dateBeg;

    @JsonProperty(required = true)
    private Date dateEnd;

    public Date getDateBeg() {
        return dateBeg;
    }

    public Date getDateEnd() {
        return dateEnd;
    }
}
