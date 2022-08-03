package fr.theialand.insitu.dataportal.api.sparql.exeception;

public class InvalidUriException extends Exception {

    public InvalidUriException(Exception ex) {
        super(ex);
    }

    public InvalidUriException(String msg, Exception ex) {
        super(msg, ex);
    }

    public InvalidUriException(String msg) {
        super(msg);
    }

}
