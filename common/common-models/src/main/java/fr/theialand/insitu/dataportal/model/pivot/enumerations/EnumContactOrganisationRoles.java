/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.model.pivot.enumerations;


import java.util.Arrays;

/**
 *
 * @author coussotc
 */
public enum EnumContactOrganisationRoles {
    RESEARCH_GROUP("Research group");
    
    String roleString;

    EnumContactOrganisationRoles(String roleString) {
        this.roleString = roleString;
    }


    @Override
    public String toString() {
    return this.roleString;
}


    /**
     * Get Enum By Value, for example getEnum("research group") will return Enum.RESEARCH_GROUP
     * @param value the string to search for
     * @throws IllegalArgumentException if not found
     * @return EnumContactOrganisationRoles
     */
    public static EnumContactOrganisationRoles getEnum(String value ) {
        return Arrays.stream(EnumContactOrganisationRoles.values())
                .filter(enumEnv -> enumEnv.roleString.equals( value ))
                .findAny().orElseThrow(  IllegalArgumentException::new )  ;
    }

}

