package fr.theialand.insitu.dataportal.backendassociation.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author coussotc
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Configure Swagger2 documentation autogeneration
     *
     * @return Docket
     */
    @Bean
    public OpenAPI variableAssociationInterfaceAPI() {
        return new OpenAPI()
                .info(new Info().title("Theia OZCAR variable association interface API")
                        .description("This API has been created to serve the variable association interface only and you should not expect to get understandable return using this API otherwise."));
    }
}
