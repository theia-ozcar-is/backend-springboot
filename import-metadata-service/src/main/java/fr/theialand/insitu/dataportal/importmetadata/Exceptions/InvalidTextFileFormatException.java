/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Thrown if a line of a csv file does not follow the defined pattern
 * @author coussotc
 */
public class InvalidTextFileFormatException extends ImportZipFileException {

    private final String line;
    private final int lineNumber;

    public InvalidTextFileFormatException(String line, int lineNumber, ZipEntry txtFile, ZipFile zipFile) {
        super(String.format("The text file does not match the defined pattern at line %d : line %s",
                lineNumber, line),
                zipFile, txtFile);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
