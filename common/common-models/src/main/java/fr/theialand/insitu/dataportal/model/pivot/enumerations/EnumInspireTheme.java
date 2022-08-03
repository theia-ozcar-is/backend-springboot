package fr.theialand.insitu.dataportal.model.pivot.enumerations;

import java.net.URI;
import java.util.Arrays;

public enum EnumInspireTheme {
    ADDRESSES("http://inspire.ec.europa.eu/theme/ad", "Addresses"),
    ADMINISTRATIVE_UNITS("http://inspire.ec.europa.eu/theme/au", "Administrative units"),
    COORDINATE_SYSTEMS("http://inspire.ec.europa.eu/theme/rs", "Coordinate reference systems"),
    GEOGRAPHICAL_SYSTEMS("http://inspire.ec.europa.eu/theme/gg", "Geographical grid systems"),
    CADASTRAL_SYSTEMS("http://inspire.ec.europa.eu/theme/cp", "Cadastral parcels"),
    GEOGRAPHICAL_NAMES("http://inspire.ec.europa.eu/theme/gn", "Geographical names"),
    HYDROGRAPHY("http://inspire.ec.europa.eu/theme/hy", "Hydrography"),
    PROTECTED_SITES("http://inspire.ec.europa.eu/theme/ps", "Protected sites"),
    TRANSPORT_NETWORKS("http://inspire.ec.europa.eu/theme/tn", "Transport networks"),
    ELEVATION("http://inspire.ec.europa.eu/theme/el", "Elevation"),
    GEOLOGY("http://inspire.ec.europa.eu/theme/ge", "Geology"),
    LAND_COVER("http://inspire.ec.europa.eu/theme/lc", "Land cover"),
    ORTHOIMAGERY("http://inspire.ec.europa.eu/theme/oi", "Orthoimagery"),
    AGRICULTURAL_FACILITIES("http://inspire.ec.europa.eu/theme/af", "Agricultural and aquaculture facilities"),
    AREA_MANAGEMENT("http://inspire.ec.europa.eu/theme/am", "Area management/restriction/regulation zones and reporting units"),
    ATMOSPHERIC_CONDITIONS("http://inspire.ec.europa.eu/theme/ac", "Atmospheric conditions"),
    BIO_GEOGRAPHICAL_REGIONS("http://inspire.ec.europa.eu/theme/br", "Bio-geographical regions"),
    BUILDINGS("http://inspire.ec.europa.eu/theme/bu", "Buildings"),
    ENERGY_RESOURCES("http://inspire.ec.europa.eu/theme/er", "Energy resources"),
    ENVIRONMENTAL_MONITORING("http://inspire.ec.europa.eu/theme/ef", "Environmental monitoring facilities"),
    BIOTOPES("http://inspire.ec.europa.eu/theme/hb", "Habitats and biotopes"),
    HEALTH_AND_SAFETY("http://inspire.ec.europa.eu/theme/hh", "Human health and safety"),
    LAND_USE("http://inspire.ec.europa.eu/theme/lu", "Land use"),
    MINERAL_RESOURCES("http://inspire.ec.europa.eu/theme/mr", "Mineral resources"),
    NATURAL_RISK_ZONES("http://inspire.ec.europa.eu/theme/nz", "Natural risk zones"),
    OCEAN_FEATURES("http://inspire.ec.europa.eu/theme/of", "Oceanographic geographical features"),
    DEMOGRAPHY("http://inspire.ec.europa.eu/theme/pd", "Population distribution — demography"),
    PRODUCTION_FACILITIES("http://inspire.ec.europa.eu/theme/pf", "Production and industrial facilities"),
    SEA_REGIONS("http://inspire.ec.europa.eu/theme/sr", "Sea regions"),
    SOIL("http://inspire.ec.europa.eu/theme/so", "Soil"),
    SPECIES_DISTRIBUTION("http://inspire.ec.europa.eu/theme/sd", "Species distribution"),
    STATS_UNIT("http://inspire.ec.europa.eu/theme/su", "Statistical units"),
    UTILITY_GOVERNMENT("http://inspire.ec.europa.eu/theme/us", "Utility and governmental services"),
    METEO("http://inspire.ec.europa.eu/theme/mf", "Meteorological geographical features"),
    ATMOSPHERIC_METEO("http://inspire.ec.europa.eu/theme/ac-mf", "Atmospheric Conditions and meteorological geographical features");

    private final URI themeUrl;
    private final String themeName;

    EnumInspireTheme( String uri, String enName ) {
        this.themeUrl = URI.create(uri);
        this.themeName   = enName;
    }

    public URI getThemeUrl() {
        return themeUrl;
    }

    public String getThemeName() {
        return themeName;
    }

    /**
     * Get Enum By Value, for example getEnum("research group") will return Enum.RESEARCH_GROUP
     *
     * @param value the string to search for
     * @return Enum
     * @throws IllegalArgumentException if not found
     */
    public static EnumInspireTheme getEnumIgnoringCase(String value) {
        return Arrays.stream(EnumInspireTheme.values())
                .filter(enumEnv -> enumEnv.getThemeName().equalsIgnoreCase(value))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return this.themeName;
    }

}
