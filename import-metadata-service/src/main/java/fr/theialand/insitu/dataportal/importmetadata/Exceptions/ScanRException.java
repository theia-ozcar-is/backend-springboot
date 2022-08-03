package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

public class ScanRException extends ImportException {

    private final String problematicId;

    public ScanRException(String msg, String id, Exception ex) {
        super(msg, ex);
        this.problematicId = id;
    }

    public ScanRException(String msg, String id) {
        super(msg);
        this.problematicId = id;

    }

    public String getProblematicId() {
        return problematicId;
    }

}
