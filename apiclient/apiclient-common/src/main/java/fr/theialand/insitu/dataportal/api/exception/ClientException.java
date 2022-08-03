package fr.theialand.insitu.dataportal.api.exception;

public class ClientException extends Exception {
    public ClientException(String msg) {
        super(msg);
    }

    public ClientException(String msg, Exception ex) {
        super(msg, ex);
    }
}
