package fr.theialand.insitu.dataportal.api.sparql;

import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClient;
import fr.theialand.insitu.dataportal.api.sparql.query.RDFQueryClientImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest( classes = RDFQueryClientImpl.class )
@Disabled("insitu.theia-land is not accessible from CI runner")
class RDFqueryIT {

    @Autowired
    RDFQueryClient rdfClient ;

    @Test
    @DisplayName("list variable should not be empty")
    void testlistVariables() {
        List<String> varsUri = rdfClient.findAllVariableUri();
        assertThat(varsUri).isNotEmpty();
    }
}
