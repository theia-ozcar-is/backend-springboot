/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.io.FileNotFoundException;

/**
 * Thrown if no json file is found in a folder
 * @author coussotc
 */
public class JsonFileNotFoundException extends ImportException {

    public JsonFileNotFoundException(String msg) {
        super(msg,new FileNotFoundException());
    }
}
