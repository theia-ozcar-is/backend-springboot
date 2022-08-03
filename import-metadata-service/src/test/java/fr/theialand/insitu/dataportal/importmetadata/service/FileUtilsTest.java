package fr.theialand.insitu.dataportal.importmetadata.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileUtilsTest {

    @TempDir
    static File tempDir;

    @BeforeAll
    static void populateTempDir() throws IOException {

        // write 4 files in the temp dir
        for(int i=0; i<4; i++)
            Files.writeString( tempDir.toPath().resolve("test"+i+".ext"),"whatever", StandardOpenOption.CREATE) ;
    }

    @Test
    void testListFilesStringShouldReturnAllZips(){

        List<File> files = FileUtils.listFiles( tempDir.toString(), "ext");

        assertThat(files).isNotEmpty().hasSize(4);
    }

    @Test
    void testListFilesStringShouldReturnemptyListWhenNotFound(){
        List<File> files = FileUtils.listFiles( tempDir.toString(), "nope");

        assertThat(files).isEmpty();
    }

    @Test
    void testListFilesPathShouldReturnAllZips() throws IOException {
        List<File> files = FileUtils.listFiles( tempDir, "ext");

        assertThat(files).isNotEmpty().hasSize(4);
    }

    @Test
    void testListFilesPathShouldReturnemptyListWhenNotFound() throws IOException {
        List<File> files = FileUtils.listFiles( tempDir, "nope");

        assertThat(files).isEmpty();
    }

}
