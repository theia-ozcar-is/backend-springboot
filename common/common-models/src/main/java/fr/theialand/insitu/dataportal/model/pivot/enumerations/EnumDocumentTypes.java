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
public enum EnumDocumentTypes {
    PUBLICATION("Publication"),
    MANUAL("Manual");
    
    private final String docType;

    EnumDocumentTypes(String docType) {
        this.docType = docType;
    }
    
    @Override
    public String toString() {
        return this.docType;
    }
}
