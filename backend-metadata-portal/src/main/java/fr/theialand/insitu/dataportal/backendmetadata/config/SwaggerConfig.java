/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.backendmetadata.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 *
 * @author coussotc
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI dataPortalInterfaceAPI() {
        return new OpenAPI()
                .info(new Info().title("Theia OZCAR user interface API")
                        .description("Theia OZCAR data portal interface API. This API has been created to serve the user interface only and you should not expect to get understandable return using this API otherwise.")
                        .version("beta-release"));
    }
}
