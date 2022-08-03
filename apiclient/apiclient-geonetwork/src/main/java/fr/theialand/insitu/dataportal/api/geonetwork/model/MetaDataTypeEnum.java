package fr.theialand.insitu.dataportal.api.geonetwork.model;

/**
 * we need to specify the type of data we are posting to GN
 */
public enum MetaDataTypeEnum {
    METADATA,
    TEMPLATE,

    SUB_TEMPLATE,
    TEMPLATE_OF_SUB_TEMPLATE;
}
