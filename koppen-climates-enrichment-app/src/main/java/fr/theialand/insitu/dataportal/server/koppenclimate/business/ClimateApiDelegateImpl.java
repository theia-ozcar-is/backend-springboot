package fr.theialand.insitu.dataportal.server.koppenclimate.business;

import fr.theialand.insitu.dataportal.server.koppenclimate.api.ClimateApiDelegate;
import fr.theialand.insitu.dataportal.server.koppenclimate.model.KoppenClimate;
import fr.theialand.insitu.dataportal.server.koppenclimate.model.KoppenClimateCodeLiteral;
import fr.theialand.insitu.dataportal.server.koppenclimate.model.LocationKoppenClimateDTO;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.util.factory.Hints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClimateApiDelegateImpl implements ClimateApiDelegate {


    private static final Logger LOG = LoggerFactory.getLogger(ClimateApiDelegateImpl.class);

    List<List<String>> koppenClasses = new ArrayList<>();

    public ClimateApiDelegateImpl() {
        try (BufferedReader br = new BufferedReader(new FileReader(ClimateApiDelegateImpl.class.getClassLoader().getResource("koppen-geiger_climate_classification_legend.csv").getFile()))) {
            String line;
            //skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                koppenClasses.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            LOG.error("Unable to read Koppen climate classification description file - koppen-geiger_climate_classification_legend.csv");
            System.exit(1);
        }
    }

    /**
     * Query the climate at one location using Beck et al. 2018 Beck_KG_V1_present_conf_0p0083.tif geotiff.
     * @param location Location in latitude, longitude format, similar to the Google Elevation API (required)
     * @return ResponseEntity<LocationKoppenClimateDTO> with the code 200 if the query succeded. Otherwise the code 400 is
     * returned
     */
    @Override
    public ResponseEntity<LocationKoppenClimateDTO> getClimate(String location) {
    URL url = ClimateApiDelegateImpl.class.getClassLoader().getResource("Beck_KG_V1_present_0p0083.tif");

        LOG.info("Enriching with koppen climate features at location {}", location);

        LocationKoppenClimateDTO locationKoppenClimateDTO = new LocationKoppenClimateDTO();

        GeoTiffFormat format = new GeoTiffFormat();
        Hints hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
        GeoTiffReader reader = format.getReader(url, hints);
        try {
            GridCoverage2D coverage = reader.read(null);
            DirectPosition2D position2D = new DirectPosition2D();
            String[] latlong = location.split(",");
            locationKoppenClimateDTO.setLatitude(Double.valueOf(latlong[0]));
            locationKoppenClimateDTO.setLongitude(Double.valueOf(latlong[1]));
            position2D.setLocation(locationKoppenClimateDTO.getLongitude(),locationKoppenClimateDTO.getLatitude());
            int[] climateCode = new int[1];
            coverage.evaluate(position2D.toPoint2D(), climateCode);
            locationKoppenClimateDTO.setKoppenClimate(setKoppenClimateFromRasterCode(climateCode[0]));
        } catch (IOException e) {
            LOG.error("Unable to read given locations on the GeoTiff file");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(locationKoppenClimateDTO,HttpStatus.OK);
    }

    private KoppenClimate setKoppenClimateFromRasterCode(int code) {
        Optional<List<String>> climateCsv = koppenClasses.stream().filter(e -> code == Integer.valueOf(e.get(0)).intValue()).findFirst();
        if (climateCsv.isPresent()) {
            KoppenClimate koppenClimate = new KoppenClimate();
            KoppenClimateCodeLiteral koppenClimateCodeLiteralClassification = new KoppenClimateCodeLiteral();
            koppenClimateCodeLiteralClassification.setCode(climateCsv.get().get(1));
            koppenClimateCodeLiteralClassification.setLiteral(climateCsv.get().get(2));
            koppenClimate.setClassification(koppenClimateCodeLiteralClassification);
            KoppenClimateCodeLiteral koppenClimateCodeLiteralMainGroup = new KoppenClimateCodeLiteral();
            koppenClimateCodeLiteralMainGroup.setCode(climateCsv.get().get(3));
            koppenClimateCodeLiteralMainGroup.setLiteral(climateCsv.get().get(4));
            koppenClimate.setMainGroup(koppenClimateCodeLiteralMainGroup);

            //Polar climates don't have seasonal precipitation subgroup
            if (climateCsv.get().get(5) == "" && climateCsv.get().get(6) == ""){
                KoppenClimateCodeLiteral koppenClimateCodeLiteralseasonPrecip= new KoppenClimateCodeLiteral();
                koppenClimateCodeLiteralseasonPrecip.setCode(climateCsv.get().get(5));
                koppenClimateCodeLiteralseasonPrecip.setLiteral(climateCsv.get().get(6));
                koppenClimate.setSeasonalPrecipitationSubGroup(koppenClimateCodeLiteralseasonPrecip);
            }
            //Af, Am, Aw climates don't have seasonal temperature subgroup
            if (climateCsv.get().size() > 7){
                KoppenClimateCodeLiteral koppenClimateCodeLiteralTemperature= new KoppenClimateCodeLiteral();
                koppenClimateCodeLiteralTemperature.setCode(climateCsv.get().get(7));
                koppenClimateCodeLiteralTemperature.setLiteral(climateCsv.get().get(8));
                koppenClimate.setTemperatureSubGroup(koppenClimateCodeLiteralTemperature);
            }

            return koppenClimate;
        } else if(code == 0 ){
            //Code for the sea. No climates are described
            return null;
        } else {
            LOG.error("Koppen Climate code {} not found in the Koppen climate classification description file - koppen-geiger_climate_classification_legend.csv", code);
            System.exit(1);
            return null;
        }
    }
}
