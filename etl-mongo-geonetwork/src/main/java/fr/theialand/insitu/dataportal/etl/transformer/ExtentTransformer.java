package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Metadata;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.SpatialExtent;
import org.apache.sis.internal.jaxb.ModifiableIdentifierMap;
import org.apache.sis.metadata.iso.extent.DefaultExtent;
import org.apache.sis.metadata.iso.extent.DefaultGeographicBoundingBox;
import org.apache.sis.metadata.iso.extent.DefaultTemporalExtent;
import org.apache.sis.referencing.CommonCRS;
import org.apache.sis.xml.IdentifiedObject;
import org.apache.sis.xml.IdentifierMap;
import org.apache.sis.xml.IdentifierSpace;
import org.geotoolkit.data.geojson.binding.GeoJSONGeometry;
import org.geotoolkit.data.geojson.binding.GeoJSONObject;
import org.geotoolkit.data.geojson.utils.GeoJSONParser;
import org.geotoolkit.data.geojson.utils.GeometryUtils;
import org.geotoolkit.geometry.jts.JTS;
import org.geotoolkit.temporal.factory.DefaultTemporalFactory;
import org.geotoolkit.temporal.object.DefaultPeriod;
import org.locationtech.jts.geom.Geometry;
import org.opengis.geometry.Envelope;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.operation.TransformException;
import org.opengis.temporal.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * All things converting geographic and temporal from Theia to ISO-apache SIS
 * package visibility, because it should not be used directly
 */
class ExtentTransformer {

    /** hiding this constructor */
    private ExtentTransformer() {}

    private static final Logger LOG = LoggerFactory.getLogger(ExtentTransformer.class);

    /**
     * Prepare the Ex_Extent part of the MD
     * consisting of 2 parts : Temporal and Geographic
     * @param metadata home of @{@link SpatialExtent} and @{@link fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent}
     * @return iso19115 @{@link DefaultExtent} covering both Spatial and Temporal
     * @throws EtlTransformerException if BBox cannot be calculated by our lib
     */
    static DefaultExtent getExtentFromMetadata(@NonNull Metadata metadata) throws EtlTransformerException {

        DefaultExtent extent = new DefaultExtent();
        extent.setTemporalElements(   convertTemporalExtent(metadata.getTemporalExtent() ));
        extent.setGeographicElements( convertSpatialExtent(metadata.getSpatialExtent()   ));
        return extent;
    }

    // INNER METHODS & CLAS

    /**
     * Convert  Spatial extents, ie get the BBOX out of our GeoJson Object
     * @param spatialExtent, theia format (geojson)
     * @return BBox as a singleton list of @{@link GeographicBoundingBox}
     * @throws EtlTransformerException if sh*t goes sideways, which is, very likely atm
     */
    private static List<GeographicBoundingBox> convertSpatialExtent(SpatialExtent spatialExtent) throws EtlTransformerException {
        if ( spatialExtent == null ) {
             LOG.warn("a metadata read from Mongo has a null spatialExtent");
            //throw new EtlTransformerException("I")
            return Collections.emptyList();
        }
        // need to get rid of this dependency, used solely for here. note the "normalized"!
        GeographicCRS crs = CommonCRS.WGS84.normalizedGeographic();

        LOG.info("converting SpatialExtent of type {}, geometry {}", spatialExtent.getType(), spatialExtent.getGeometry().getType());
        // using JTS sub package of geotoolkit for parsing our custom homemade object into a geojson one
        String jsonString  ;
        GeoJSONObject geoJSONObject;
        try {
            jsonString = spatialExtent.getGeometry().toJson();
            // !!! The Parser only accept geojson with "type" first (before coords) ! bug.
            // This Object is in fact a GeoJsonGeometry, much more useful, once you know it
            geoJSONObject = GeoJSONParser.parse(new ByteArrayInputStream( jsonString.getBytes()));
        } catch (IOException ioEx) {
            throw new EtlTransformerException("cant parse", ioEx);
        }
        GeoJSONGeometry geoJsonGeom = (GeoJSONGeometry) geoJSONObject;

        Geometry geom = GeometryUtils.toJTS(geoJsonGeom, crs);
        Envelope env = JTS.toEnvelope(geom);

        DefaultGeographicBoundingBox bbox = new DefaultGeographicBoundingBox();
        try {
            bbox.setBounds( env );
        } catch (TransformException transfoEx) {
            throw new EtlTransformerException("can't get BBOX from given Envelope", transfoEx );
        }

        // Inspire require at least 2 decimal digits...
        GeographicBoundingBox enhancedBbox = ensure2digitsPrecision(bbox);

        // wow, it was tricky.
        return Collections.singletonList(enhancedBbox);
    }

    /**
     * Convert a pivot TemporalExtent to an ISO Temporal Extent
     * @param temporalExtent as in our pivot model
     * @return empty if incoming temporalExtent is null, but it should not happen
     */
    private static List<TemporalExtent> convertTemporalExtent(fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.TemporalExtent temporalExtent) throws EtlTransformerException {
        if ( temporalExtent == null ) {
            // LOGGER.warn("a metadata read from Mongo has a null temporalExtent")
            throw new EtlTransformerException("no TemporalExtent defined");
        }
        LOG.info("converting temporalExtent {} <-> {}", temporalExtent.getDateBeg(), temporalExtent.getDateEnd());

        Instant instantBeg = new DefaultTemporalFactory().createInstant(temporalExtent.getDateBeg());
        Instant instantEnd = new DefaultTemporalFactory().createInstant(temporalExtent.getDateEnd());

        // To be able to add the mandatory ID to TimePeriod, we need to go through great lengths...
        // https://www.mail-archive.com/user@sis.apache.org/msg00031.html
        final Map<String, Object> prop = new HashMap<>();
        prop.put(org.opengis.referencing.IdentifiedObject.NAME_KEY, "period1"  );
        IdentifiedPeriod customTimePeriod = new IdentifiedPeriod(
                prop, instantBeg, instantEnd );
        customTimePeriod.getIdentifierMap().putSpecialized(IdentifierSpace.ID, "TimePeriod1");

        DefaultTemporalExtent tempoExtent = new DefaultTemporalExtent( );
        tempoExtent.setExtent(customTimePeriod);
        return Collections.singletonList(tempoExtent);
    }

    /**
     * add some "precision" to GPS digits because INSPIRE fails on lat/lon 45/5 coords. it prefers 45.000001/5.000001 ... wtf.
     * Note that due to double internal JVM storage, this may not be 100% reliable...
     * @param bbox to "enhance" to multidecimal precision...
     * @return a new bbox with "enhanced" (*ahem*) coords
     */
    private static GeographicBoundingBox ensure2digitsPrecision(DefaultGeographicBoundingBox bbox) {
        return new DefaultGeographicBoundingBox(
                enhancePrecision(bbox.getWestBoundLongitude()),
                enhancePrecision(bbox.getEastBoundLongitude()),
                enhancePrecision(bbox.getSouthBoundLatitude()),
                enhancePrecision(bbox.getNorthBoundLatitude()) );
    }

    /**
     * If the coord is too simple, like not enough digits,
     * then we add some ...0.000001
     * @param coord double number to check for "precision"
     * @return double number with added "precision"
     */
    private static double enhancePrecision(double coord) {
        // https://www.baeldung.com/java-separate-double-into-integer-decimal-parts

        BigDecimal bigDecimal = BigDecimal.valueOf( coord );
        if( bigDecimal.scale() < 3 ) {
            LOG.debug("coordinate {} is not precise enough for Inspire, adding a few decimal 'precision'", coord);
            return coord + 0.000000001 ;
        } else {
            return coord;
        }
    }

    /**
     * To be able to add the mandatory ID to TimePeriod, we need to go through great lengths...
     * https://www.mail-archive.com/user@sis.apache.org/msg00031.html
     */
    private static class IdentifiedPeriod extends DefaultPeriod implements IdentifiedObject {
        private final List<Identifier> identifiers = new ArrayList<>();

        public IdentifiedPeriod(Map<String, ?> properties, org.opengis.temporal.Instant begining, org.opengis.temporal.Instant ending) {
            super(properties, begining, ending);
        }

        @Override
        public Set<Identifier> getIdentifiers() {
            return new HashSet<>(identifiers);
        }

        @Override
        public IdentifierMap getIdentifierMap() {
            return new ModifiableIdentifierMap( identifiers);
        }
    }

}
