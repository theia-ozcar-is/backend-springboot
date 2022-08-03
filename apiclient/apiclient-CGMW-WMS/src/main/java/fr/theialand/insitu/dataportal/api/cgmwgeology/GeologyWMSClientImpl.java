package fr.theialand.insitu.dataportal.api.cgmwgeology;

import fr.theialand.insitu.dataportal.api.cgmwgeology.model.CGMWGeology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GeologyWMSClientImpl implements GeologyWMSClient {

    private static final Logger LOG = LoggerFactory.getLogger(GeologyWMSClientImpl.class);

    private Map<List<Double>,CGMWGeology> geologyCache =  new HashMap<>();

    @Value("${cgmw.geogloy.wms.url}")
    private String wmsUrl;
    private final String service = "WMS";
    private final String version = "1.3.0";
    private final String request = "GetFeatureInfo";
    private final String queryLayer = "World_CGMW_50M_GeologicalUnitsOnshore";
    private final String infoFormat = "text/plain";
    private final String crs = "EPSG:4326";

    // How to properly query WMS getFeatureInfo?
    // WMS getFeature requests the spatial and attribute data for the features at a given location on a map (the feature at one point on the map).
    // ----- Query parameter:
    // BBOX: Bounding box for map extent. Value is minx,miny,maxx,maxy in units of the SRS.
    // width: Width of map output, in pixels.
    // height: Height of map output, in pixels.
    // i: X ordinate of query point on map, in pixels. 0 is left side. i is the parameter key used in WMS 1.3.0.
    // j: Y ordinate of query point on map, in pixels. 0 is the top. j is the parameter key used in WMS 1.3.0.
    // -----
    // The WMS method will query a map according to the BBOX parameter. The height and width parameters must respect the proportion of the
    // BBOX in order not to deform the map. For a squared BBOX height must be equal to width for the map not to be deformed.
    // I and J are used to query the feature info on one location (pixel) on the returned map.
    // -----
    // To query the feature info on one particular point we need to form a squared BBOX around the location,
    // I must be equal to J to query the center of the BBOX and height and width must be equal to I * 2 + 1 in order
    // to query the center pixel of the map.

    private final String width = "101";
    private final String height = "101";
    private final String i = "50";
    private final String j = "50";

    @Override
    public CGMWGeology getGeology(List<Double> location) {

        Optional<Map.Entry<List<Double>, CGMWGeology>> geologyCached = this.geologyCache.entrySet().stream().filter(
                e -> e.getKey().get(0).equals(location.get(0)) && e.getKey().get(1).equals(location.get(1))).findFirst();

        if (geologyCached.isPresent()){
            return geologyCached.get().getValue();
        } else {

            // !!! geojson is Long Lat whereas WMS x,y is Lat Long
            Double xmin = location.get(1) - 0.01;
            Double xmax = location.get(1) + 0.01;
            Double ymin = location.get(0) - 0.01;
            Double ymax = location.get(0) + 0.01;

            URL url = null;
            String urlString =wmsUrl + "?SERVICE=" + service + "&VERSION=" + version + "&REQUEST=GetFeatureInfo&LAYERS=" + queryLayer +"&QUERY_LAYERS="+queryLayer+ "&STYLES=&INFO_FORMAT=" + URLEncoder.encode(infoFormat, StandardCharsets.UTF_8)
                    + "&CRS=" + URLEncoder.encode(crs, StandardCharsets.UTF_8)+ "&BBOX=" + xmin + "," + ymin + "," + xmax + "," + ymax + "&WIDTH=" + width + "&HEIGHT=" + height + "&I=" + i + "&J=" + j;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                LOG.error("MalformedURLException for the URL : {}", urlString);
            }

            String inputLine = null;
            CGMWGeology cgmwGeology = new CGMWGeology();
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    LOG.info("Reading WMS getFeature for location " +location.get(0) + ", " + location.get(1));
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("LITHO_EN = '")){
                            cgmwGeology.setLitho_en(inputLine.substring(inputLine.indexOf("'")+1,inputLine.lastIndexOf("'")));
                        } else if (inputLine.contains("STRATI_EN = '")){
                            cgmwGeology.setStrati_en(inputLine.substring(inputLine.indexOf("'")+1,inputLine.lastIndexOf("'")));
                        } else if (inputLine.contains("DESCR_EN = '")){
                            cgmwGeology.setDescr_en(inputLine.substring(inputLine.indexOf("'")+1,inputLine.lastIndexOf("'")));
                        }
                    }
                }
            } catch (IOException e) {
                LOG.error("Cannot read from the WMS service. Check that the service is alive and check the url used is not malformed. Url used : {}", url);
            } finally {
                con.disconnect();
            }
            //Store the new geology for a given location in the cache map
            this.geologyCache.put(location,cgmwGeology);

            return cgmwGeology;
        }
    }
}
