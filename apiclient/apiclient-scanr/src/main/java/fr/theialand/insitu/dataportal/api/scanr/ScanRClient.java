package fr.theialand.insitu.dataportal.api.scanr;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullStructure;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface ScanRClient {

    @Nullable V2FullStructure getStructure(@NonNull String scanrId) throws ClientException;
}
