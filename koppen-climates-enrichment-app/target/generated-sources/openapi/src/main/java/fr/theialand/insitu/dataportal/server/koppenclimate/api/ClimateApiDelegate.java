package fr.theialand.insitu.dataportal.server.koppenclimate.api;

import fr.theialand.insitu.dataportal.server.koppenclimate.model.LocationKoppenClimateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * A delegate to be called by the {@link ClimateApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-15T15:48:18.579958+02:00[Europe/Paris]")
public interface ClimateApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /climate
     * Returns the koppen climate code at one (latitude,longitude) point. 
     *
     * @param location Location in latitude, longitude format, similar to the Google Elevation API (required)
     * @return A JSON object with a single result containing latitude, longitude and koppenClimate. (status code 200)
     * @see ClimateApi#getClimate
     */
    default ResponseEntity<LocationKoppenClimateDTO> getClimate(String location) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"latitude\" : 0.8008281904610115, \"koppenClimate\" : { \"mainGroup\" : { \"code\" : \"code\", \"literal\" : \"literal\" }, \"seasonalPrecipitationSubGroup\" : { \"code\" : \"code\", \"literal\" : \"literal\" }, \"classification\" : { \"code\" : \"code\", \"literal\" : \"literal\" }, \"temperatureSubGroup\" : { \"code\" : \"code\", \"literal\" : \"literal\" } }, \"longitude\" : 6.027456183070403 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
