/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation;

import java.util.ArrayList;

/**
 *
 * @author coussotc
 */
public class Procedure {

    /**
     * List of the LineageInformation object describing the diffrent processes the observation underwent before to
     * present the final result
     */
    private ArrayList<LineageInformation> lineageInformations;
    /**
     * DataProduction object describing the dataProduction process of the observation
     */
    private DataProduction dataProduction;

    public ArrayList<LineageInformation> getLineageInformations() {
        return lineageInformations;
    }

    public void setLineageInformations(ArrayList<LineageInformation> lineageInformations) {
        this.lineageInformations = lineageInformations;
    }

    public DataProduction getDataProduction() {
        return dataProduction;
    }

    public void setDataProduction(DataProduction dataProduction) {
        this.dataProduction = dataProduction;
    }
    

}
