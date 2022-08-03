package fr.theialand.insitu.dataportal.server.koppenclimate.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-15T15:48:18.579958+02:00[Europe/Paris]")
@Controller
@RequestMapping("${openapi.koppenClimate.base-path:/}")
public class ClimateApiController implements ClimateApi {

    private final ClimateApiDelegate delegate;

    public ClimateApiController(@Autowired(required = false) ClimateApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new ClimateApiDelegate() {});
    }

    @Override
    public ClimateApiDelegate getDelegate() {
        return delegate;
    }

}
