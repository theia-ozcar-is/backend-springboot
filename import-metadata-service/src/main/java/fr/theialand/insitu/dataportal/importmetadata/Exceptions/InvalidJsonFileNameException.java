/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.util.regex.Pattern;

/**
 * Thrown if the json file name does not follow the name pattern
 * @author coussotc
 */
public class InvalidJsonFileNameException extends ImportException {
    
    public InvalidJsonFileNameException(String jsonFileName, Pattern regex) {
        super(jsonFileName + " does not match the json file name pattern: "+regex.toString());
    }

}
