package fr.theialand.insitu.dataportal.etl.exception;

/**
 * extended by "nice fails" of the ETL module
 */
public abstract class EtlException extends Exception{
    EtlException(String msg, Exception ex) {
        super(msg,ex);
    }

    public EtlException(String msg) {
        super(msg);
    }

    public EtlException(Exception ex) {
        super(ex);
    }
}
