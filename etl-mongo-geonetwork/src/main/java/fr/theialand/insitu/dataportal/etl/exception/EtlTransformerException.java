package fr.theialand.insitu.dataportal.etl.exception;

/**
 * When something related to the workflow fails in the conversion process
 */
public class EtlTransformerException extends EtlException {

    public EtlTransformerException(String msg) {
        super(msg);
    }

    public EtlTransformerException(String msg, Exception ex) {
        super(msg, ex);
    }
}
