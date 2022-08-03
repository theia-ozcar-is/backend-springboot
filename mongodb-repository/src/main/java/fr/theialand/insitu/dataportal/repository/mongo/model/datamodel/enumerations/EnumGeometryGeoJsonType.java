package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations;

public enum EnumGeometryGeoJsonType {

    POINT("Point"),
    MULTIPOINT("MultiPoint"),
    LINESTRING("LineString"),
    MULTILINESTRING("MultiLineString"),
    POLYGON("Polygon"),
    MULTIPOLYGON("MultiPolygon");

    private String enumGeometryGeoJsonType;

    private EnumGeometryGeoJsonType(String enumGeoJSONType) {
        this.enumGeometryGeoJsonType = enumGeoJSONType;
    }

    @Override
    public String toString() {
        return this.enumGeometryGeoJsonType;
    }
}
