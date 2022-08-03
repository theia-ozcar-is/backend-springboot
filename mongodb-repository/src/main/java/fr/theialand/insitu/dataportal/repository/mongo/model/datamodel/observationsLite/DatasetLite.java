/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observationsLite;

/**
 *
 * @author coussotc
 */
public class DatasetLite {
        /**
     * ID of the dataset. trigramme of the provider plus the number of the dataset separated with underscore ex: AMA_1
     */
    private String datasetId;
    /**
     * Metadata object representing the metadata information of the dataset
     *
     * @see Metadata
     */
    private MetadataLite metadata;

    public String getDatasetId() {
        return datasetId;
    }

    public MetadataLite getMetadata() {
        return metadata;
    }
    
}
