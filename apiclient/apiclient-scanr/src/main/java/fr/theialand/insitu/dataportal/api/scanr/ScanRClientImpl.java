package fr.theialand.insitu.dataportal.api.scanr;

import fr.theialand.insitu.dataportal.api.exception.ClientException;
import fr.theialand.insitu.dataportal.api.exception.ClientLinkException;
import fr.theialand.insitu.dataportal.api.scanr.api.StructureApi;
import fr.theialand.insitu.dataportal.api.scanr.model.V2FullStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

@Service
public class ScanRClientImpl implements ScanRClient  {

    private final StructureApi structureApi;

    @Autowired
    ScanRClientImpl( StructureApi structureApi ) {
        this.structureApi = structureApi;
    }

    @Override
    @Nullable
    public V2FullStructure getStructure(@NonNull String scanrId ) throws ClientException {
        try {
            return structureApi.getStructure(scanrId );
        } catch (RestClientResponseException apiEx) {
            throw new ClientException("Error received when communicating with ScanR",apiEx);
        } catch (ResourceAccessException linkEx) {
            throw new ClientLinkException("Link error received", linkEx);
        }
    }
}
