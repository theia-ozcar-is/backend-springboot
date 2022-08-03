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
public class TheiaCategoriesException extends ImportException {

    public TheiaCategoriesException(String uri) {
        super("Theia Categories " + uri + " does not exists in the thesaurus");
    }

}
