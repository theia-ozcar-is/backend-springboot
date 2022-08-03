package fr.theialand.insitu.dataportal.etl;

import fr.theialand.insitu.dataportal.etl.exception.EtlException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * ETL Service between Mongo and Geonetwork
 *
 * Entry point
 * ... to be called by import-module for example
 */
@Service
public interface EtlService {

    /**
     * will DELETE all datasets in GN matching this producer, and reupload them based on mongo
     * @param producerId 4-letter , should be an enum as it is hardcoded in code anyway. see @{@link fr.theialand.insitu.dataportal.model.pivot.enumerations.EnumProducer}
     * @return unused list of UUID
     * @throws EtlException for all kind of "expected failures"
     */
    List<UUID> updateAllDatasetsFromProducer(@NonNull String producerId) throws EtlException;

    /**
     * Update a single dataset in GN
     * @param datasetId
     * @return unused UUID
     * @throws EtlException for all kind of "expected failures"
     */
    UUID updateDataset(@NonNull String datasetId) throws EtlException;

}
