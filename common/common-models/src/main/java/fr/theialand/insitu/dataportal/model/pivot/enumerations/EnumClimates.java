/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.enumerations;


import fr.theialand.insitu.dataportal.model.pivot.dataset.PortalSearchCriteria;

/**
 * Enumeration of climate possibilities for PortalSearchCriteria Object
 * @see PortalSearchCriteria
 * @author coussotc
 */
public enum EnumClimates {
    OCEANIC("Oceanic climate"),
    TROPICAL("Tropical climate"),
    MEDITERRANEAN("Mediterranean climate"),
    MOUNTAIN("Mountain climate"),
    CONTINENTAL("Continental climate"),
    POLAR("Polar climate"),
    ARID("Arid climate"),
    EQUATORIAL("Equatorial climate");
    
    private String climate;
   /**
    * Climate constructor
    * @param climate String
    */
    private EnumClimates(String climate) {
        this.climate = climate;
    }
    
    /**
     * String value of Enumeration
     * @return vlaue of the enumeration
     */
    @Override
    public String toString() {
        return this.climate;
    }
    
    /**
     * Method to instanciate a EnumClimate value if the parameter corresponds to one of the enumeration
     * @param test a string that must correspond or not to a vlaue of the enumeration
     * @return Enumclimate value
     */
    public static EnumClimates enumValueOf(String test) {
        EnumClimates en = null;
        try {
            for (EnumClimates e : EnumClimates.values()) {
                if (e.name().equals(test)) {
                    en = e;
                }
            }
        }
        catch (EnumConstantNotPresentException e) {
            e.getMessage(); // do something now
        }
        return en;
    }
}
