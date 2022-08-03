package fr.theialand.insitu.dataportal.model.pivot.enumerations;

import java.util.Arrays;

public enum EnumTopicCategory {

    BOUNDARIES("Boundaries"),
    BIOTA("Biota"),
    CLIMATOLOGY_METEOROLOGY_ATMOSPHERE("Climatology / Meteorology / Atmosphere"),
    ECONOMY("Economy"),
    ELEVATION("Elevation"),
    ENVIRONMENT("Environment"),
    FARMING("Farming"),
    GEOSCIENTIFIC_INFORMATION("Geoscientific Information"),
    HEALTH("Health"),
    IMAGERY_BASE_MAPS_EARTH_COVER("Imagery / Base Maps / Earth Cover"),
    INLAND_WATERS("Inland Waters"),
    INTELLIGENCE_MILITARY("Intelligence / Military"),
    LOCATION("Location"),
    OCEANS("Oceans"),
    PLANNING_CADASTRE("Planning / Cadastre"),
    SOCIETY("Society"),
    STRUCTURE("Structure"),
    TRANSPORTATION("Transportation"),
    UTILITIES_COMMUNICATION("Utilities / Communication");

    private final String topicName;

    EnumTopicCategory(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public static EnumTopicCategory getEnum( String value ) {
        return Arrays.stream(EnumTopicCategory.values())
                .filter(enumEnv -> enumEnv.topicName.equals( value ))
                .findAny().orElseThrow( IllegalArgumentException::new );
    }

    @Override
    public String toString() {
        return topicName;
    }
}
