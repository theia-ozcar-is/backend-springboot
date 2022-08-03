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
public enum EnumFundingTypes {
    FRENCH_INSTITUTE("French research institutes"),  // new addition because of CATC
    FRENCH_UNIVERSITIES("French universities and schools"),  // new addition because of CATC
    //ORGANISATION("Organisation"), // not in the official enum
    FEDERATIVE_STRUCTURE("Federative structure"),
    RESEARCH_PROGRAM("Research program"),
    RESEARCH_UNIT("Research unit"),
    OTHER_UNIVERSITIES("Other universities and schools"), // new addition because of CATC
    OTHER_INSTITUTE("Other research institutes"),  // new addition because of CATC
    OTHER("Other");
    
    private final String fundingName;

    EnumFundingTypes(String fundingType) {
        this.fundingName = fundingType;
    }
    
    @Override
    public String toString() {
        return this.fundingName;
    }
}
