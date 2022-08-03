/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.util.regex.Pattern;

/**
 * Thrown if a string of an id does not follow the specified pattern for ids
 * @author coussotc
 */
public class InvalidIdsException extends ImportException{
    private final String id;
    private final Pattern regex;

    public InvalidIdsException(String id, String regex) {
        super("ID: " + id +" - does not follow the specified pattern"+regex+".");
        this.id = id;
        this.regex = Pattern.compile(regex);
    }

    public String getId() {
        return id;
    }

    public Pattern getRegex() {
        return regex;
    }
}
