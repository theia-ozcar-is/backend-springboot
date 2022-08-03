package fr.theialand.insitu.dataportal.api.sparql.exeception;

public class InvalidCategoryUriException extends InvalidUriException {
    public InvalidCategoryUriException(String uri) {
        super(uri + " does not correponds to a category URI");
    }
}
