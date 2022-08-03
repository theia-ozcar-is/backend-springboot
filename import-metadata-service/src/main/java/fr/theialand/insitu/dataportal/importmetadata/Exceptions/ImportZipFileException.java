package fr.theialand.insitu.dataportal.importmetadata.Exceptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ImportZipFileException extends ImportException {

    private final String zipFile;
    private final List<String> zipEntries;

    public ImportZipFileException(String msg, ZipFile problematicZipFile, ZipEntry... problematicCompressedFiles) {
        // print concerned Zip, and if any, the entries supplied
        super(msg + " zipFile: "+problematicZipFile.getName()
                + (problematicCompressedFiles.length!=0 ? "problematic File(s): "+Arrays.stream(problematicCompressedFiles).map(ZipEntry::getName).collect(Collectors.joining(",") ) :""));
        this.zipFile = problematicZipFile.getName();
        this.zipEntries = Arrays.stream(problematicCompressedFiles).map(ZipEntry::getName).collect(Collectors.toList());
    }

    public String getZipFile() {
        return zipFile;
    }

    public List<String> getZipEntries() {
        return zipEntries;
    }
}
