/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

/**
 * Thrown if a field that should be identical between two json file of different language is different
 * @author coussotc
 */
public class InvalidJsonInternationalisationException extends ImportException {

    public InvalidJsonInternationalisationException(String diff, String fileName1, String fileName2) {
        super("The files " + fileName1 + " and " + fileName2 +" differ according to the following pattern:\n " + diff);
    }

}
