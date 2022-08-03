/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

/**
 *
 * @author coussotc
 */
public class Iso3116Exception extends ImportException {

    private final String code;

    public Iso3116Exception(String msg, String countryCode) {
        super(msg);
        this.code = countryCode;
    }

    public Iso3116Exception(String msg, String countryCode, Exception ex) {
        super(msg, ex);
        this.code = countryCode;
    }

    public String getCode() {
        return code;
    }
}
