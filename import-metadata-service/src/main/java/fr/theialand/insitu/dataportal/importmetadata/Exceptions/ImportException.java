package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import org.springframework.boot.ExitCodeGenerator;

public class ImportException extends Exception implements ExitCodeGenerator {

    public ImportException(Exception ex) {
        super(ex);
    }

    public ImportException(String msg, Exception ex) {
        super(msg, ex);
    }

    public ImportException(String msg) {
        super(msg);
    }


    /**
     * should be implemented by subclasses if we want others codes
     */
    @Override
    public int getExitCode() {
        return 1;
    }
}
