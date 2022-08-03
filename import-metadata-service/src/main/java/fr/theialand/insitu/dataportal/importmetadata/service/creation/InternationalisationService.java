package fr.theialand.insitu.dataportal.importmetadata.service.creation;

import com.fasterxml.jackson.databind.JsonNode;
import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportException;;
import fr.theialand.insitu.dataportal.importmetadata.service.ImportConfig;

import java.io.IOException;

public interface InternationalisationService {
    public JsonNode setInternationalisation(String folderPath, ImportConfig importConfig) throws IOException, ImportException;
}
