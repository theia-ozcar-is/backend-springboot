/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author coussotc
 */
public enum EnumContactPersonRoles {
    PRINCIPAL_INVESTIGATOR("Principal investigator"),
    PROJECT_LEADER("Project leader"),
    PROJECT_MEMBER("Project member"),
    DATA_MANAGER("Data manager"),
    DATA_COLLECTOR("Data collector");
    
    String roleString;

    EnumContactPersonRoles(String roleString) {
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
     * @return EnumContactPersonRoles
     */
    public static EnumContactPersonRoles getEnum( String value ) {
        return Arrays.stream(EnumContactPersonRoles.values())
                .filter(enumEnv -> enumEnv.roleString.equals( value ))
                .findAny().orElseThrow( () -> new IllegalArgumentException(
                        value+" is not in the list of the allowed Person Roles: "
                                + Arrays.stream(EnumContactPersonRoles.values()).map(EnumContactPersonRoles::toString).collect(Collectors.joining(","))
                ) );
    }

}
