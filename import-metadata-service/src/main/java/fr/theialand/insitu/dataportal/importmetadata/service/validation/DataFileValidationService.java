/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.importmetadata.service.validation;

import fr.theialand.insitu.dataportal.importmetadata.Exceptions.ImportFileException;
import fr.theialand.insitu.dataportal.model.pivot.Pivot;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author coussotc
 */
public interface DataFileValidationService {

    Map<String,Boolean> validateZipFiles(Pivot pivot, List<File> zipFiles) throws IOException, ImportFileException;

}
