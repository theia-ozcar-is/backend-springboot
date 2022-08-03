package fr.theialand.insitu.dataportal.api.exception;

public class ClientNotFoundException extends ClientException {
    private final String idNotFound;

    public ClientNotFoundException(String msg, String idNotFound) {
        super(msg);
        this.idNotFound = idNotFound;
    }

    public String getIdNotFound() {
        return idNotFound;
    }

}
