package fr.theialand.insitu.dataportal.etl.transformer;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

 class IdentificationTransformerTest {

    @Test
    void testLocaleTransform() {
        Locale locFr = Locale.forLanguageTag("fr");
        Locale locEn = Locale.forLanguageTag("en");
        Locale locEs = Locale.forLanguageTag("es");

        assertThat(locFr).isEqualTo(Locale.FRENCH);
        assertThat(locEn).isEqualTo(Locale.ENGLISH);
        //assertThat(locEs).isEqualTo(Locale.); // wut ? no SPAIN locale ?
    }
}
