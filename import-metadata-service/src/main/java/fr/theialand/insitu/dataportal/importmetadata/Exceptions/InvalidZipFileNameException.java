/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

/**
 * Thrown if a zip file name does not follow the defined pattern
 * @author coussotc
 */
public class InvalidZipFileNameException extends ImportFileException{

    public InvalidZipFileNameException(String absentZipFile) {
        super("Zip file "+ absentZipFile + " not found.");
    }
    
}
