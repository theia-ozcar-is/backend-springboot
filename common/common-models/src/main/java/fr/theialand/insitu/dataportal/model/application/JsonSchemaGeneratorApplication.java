package fr.theialand.insitu.dataportal.model.application;

import fr.theialand.insitu.dataportal.model.pivot.Pivot;
import fr.theialand.insitu.dataportal.model.service.JsonSchemaGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


/**
 * This is NOT spring boot, it is not called automatically
 * To be run in the IDE or maven ! see readme
 * This is Old Skool so S-Boot doesn't *autorun* thing here.
 */
//@SpringBootApplication   // so that it is not run by Spring context
@ComponentScan("fr.theialand.insitu.dataportal.model")
public class JsonSchemaGeneratorApplication  {

    private static final String DEFAULT_OUTPUT_DRAFT_2019_09 = "pivotSchema.json";
    private static final String DEFAULT_OUTPUT_DRAFT_7 = "pivotSchemaDraft7.json";

    public static void main(String[] args) throws Exception {
        List<Path> outputFiles = new ArrayList<>();
        outputFiles.add(Path.of(DEFAULT_OUTPUT_DRAFT_2019_09));
        outputFiles.add(Path.of(DEFAULT_OUTPUT_DRAFT_7));

        // get the Context "the old way", non automated by springboot
        try {
            ApplicationContext springContext = SpringApplication.run(JsonSchemaGeneratorApplication.class, args);
            for (Path path : outputFiles) {
                System.out.println("printing schema to file " + path.toAbsolutePath().toString());

                // get the bean "the old way", no Autowired, no Component necessary
                JsonSchemaGenerator schemaGen;
                if (path.toString().equals("pivotSchemaDraft7.json")) {
                    schemaGen = (JsonSchemaGenerator) springContext.getBean("jsonSchemaGenerator","DRAFT_7", springContext);
                }else {
                    schemaGen = (JsonSchemaGenerator) springContext.getBean("jsonSchemaGenerator","DRAFT_2019_09", springContext );
                }
                String theiaSchema = schemaGen.getJsonSchemaPrettyPrint(Pivot.class);

                if (theiaSchema == null)
                    throw new RuntimeException("generation of Json Schema failed");

                Files.write(path, theiaSchema.getBytes());
                System.out.println("schema written to file " + path.toAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
