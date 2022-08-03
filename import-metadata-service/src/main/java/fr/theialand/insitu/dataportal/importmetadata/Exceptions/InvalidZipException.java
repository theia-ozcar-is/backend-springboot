/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.io.File;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * Thrown if a zip file is not readable
 * @author coussotc
 */
public class InvalidZipException extends ImportFileException {
    public InvalidZipException(File zipFileName, ZipException zipEx) {
        super(zipFileName.getName()+ " is not readable or is not a valid zip file", zipEx, zipFileName );
    }
    public InvalidZipException(String msg, ZipException zipEx) {
        super(msg, zipEx );
    }

}
