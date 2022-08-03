package fr.theialand.insitu.dataportal.etl;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import org.apache.sis.internal.jaxb.gcx.Anchor;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.apache.sis.util.iso.SimpleInternationalString;
import org.opengis.util.InternationalString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Helper class with some various methods to convert types and ease Objects manipulation
 * For apache, ETL ...
 */
public final class EtlUtils {

    /**
     * Hiding constructor, this is a helper class, full of static noise
     */
    private EtlUtils() {}

    private static final Logger LOG = LoggerFactory.getLogger(EtlUtils.class);

    /**
     * Convert our custom i18n type, List<I18n>, to another one , InternaltionalString,
     * based on a Map<Locale,String>
     *     Apache SIS has a {@link org.opengis.util.InternationalString}
     * @param i18nString our i18n string
     * @return @{@link InternationalString}
     */
    public static InternationalString convertTheiaI18nString(@NonNull List<I18n> i18nString ) {
        Assert.notEmpty( i18nString, "passed I18n object should not be empty");

        Map<Locale, String> localeMap = i18nString.stream()
                .collect( Collectors.toMap( l -> Locale.forLanguageTag(l.getLang()), I18n::getText));
        return new DefaultInternationalString(localeMap);
    }

    /**
     * Return an element providing {@link InternationalString} :
     * can be an {@link Anchor} with URI if provided
     * @param i18nString our usual list
     * @param anchorURI URI, can be null
     * @return DefaultInternationalString or {@link Anchor}
     */
    public static InternationalString createSimpleStringOrAnchor(@NonNull List<I18n> i18nString, @Nullable String anchorURI ) {
        if ( anchorURI != null ) {
            return new Anchor(URI.create(anchorURI), getENorAnyStringFromI18n( i18nString ));
        } else {
            return new SimpleInternationalString( getENorAnyStringFromI18n( i18nString ) );
        }
    }

    /**
     * Create our I18n type from an english string
     * @param englishString
     * @return
     */
    public static List<I18n> createSimpleI18nString(@NonNull String englishString ) {
        I18n i18nString = new I18n();
        i18nString.setLang("en");
        i18nString.setText(englishString);

        return List.of( i18nString );
    }

    /**
     * Return L10n string in English, or, if not found, *any other* language found
     * @param i18nString
     * @return
     */
    public static String getENorAnyStringFromI18n( @NonNull List<I18n> i18nString ) {
        Assert.notEmpty( i18nString, "passed I18n object should not be empty");
        Map<Locale, String> localeMap = i18nString.stream()
                .collect( Collectors.toMap(l -> new Locale ( l.getLang() ), I18n::getText));

        return localeMap.getOrDefault(Locale.ENGLISH, localeMap.values().iterator().next() ) ;
    }
}
