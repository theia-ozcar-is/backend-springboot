package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

public class TheiaVariableException extends ImportException {

    public TheiaVariableException(String uri) {
        super("Theia variable " + uri + " does not exists in the thesaurus");
    }

}
