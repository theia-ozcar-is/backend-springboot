/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Thrown if the json file validation using the json schema failed
 * @author coussotc
 */
public class InvalidJsonException extends ImportException{
    private final String validationOutput;

    public InvalidJsonException(String validationOutput) {
        super("Validation of JSON file format failed: "+validationOutput);
        this.validationOutput = validationOutput;
    }

    public InvalidJsonException(JsonProcessingException jacksonEx) {
        super("the syntax of the JSON file is incorrect :"+ jacksonEx.getOriginalMessage());
        this.validationOutput = jacksonEx.getMessage();
    }

    public String getValidationOutput(){
                return validationOutput;
    }
}
