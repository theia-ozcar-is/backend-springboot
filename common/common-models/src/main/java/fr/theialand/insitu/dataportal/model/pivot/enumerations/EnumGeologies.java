/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.enumerations;

/**
 *
 * @author coussotc
 */
public enum EnumGeologies {
    VOLCANIC("Volcanic rocks"),
    PLUTONIC("Plutonic rocks"),
    METAMORPHIC("Metamorphic rocks"),
    CARBONATES("Carbonate rocks"),
    QUATERNARY("Quaternary soils"), // was "Quartenary soils" with a misplaced R
    OTHER_SEDIMENTARY_ROCKS("Other sedimentary rocks");

    private final String geology;

    EnumGeologies(String geology) {
        this.geology = geology;
    }

    @Override
    public String toString() {
        return this.geology;
    }
    
    /**
     * Check if a String is a value of the Enumeration and return the value of the enumration if true
     * @param test String to fr.theialand.insitu.test
     * @return value of the enumeration
     */
    public static EnumGeologies enumValueOf(String test) {
        EnumGeologies en = null;
        try {
            for (EnumGeologies e : EnumGeologies.values()) {
                if (e.name().equals(test)) {
                    en = e;
                }
            }
        } catch (EnumConstantNotPresentException e) {
            e.getMessage(); // do something
        }
        return en;
    }
}
