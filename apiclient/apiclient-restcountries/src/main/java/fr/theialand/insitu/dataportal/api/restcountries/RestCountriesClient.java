package fr.theialand.insitu.dataportal.api.restcountries;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.restcountries.model.RestCountry;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface RestCountriesClient {

    @Nullable
    RestCountry getCountryByCode(@NonNull String countryCode ) throws ClientException;

}
