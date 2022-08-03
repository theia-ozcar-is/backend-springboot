package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * To be used by all Exception having trouble with a File (or directory), or a Filename ...
 */
public class ImportFileException extends ImportException{

    private final List<File> files;

    public ImportFileException(String msg, Exception ex, File... files) {
        super(msg, ex);
        this.files = Arrays.asList(files);
    }

    public ImportFileException(String msg, File... files) {
        super(msg);
        this.files = Arrays.asList(files);
    }

    public List<File> getProblematicFile() {
        return files;
    }
}
