package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.DataConstraint;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Embargo;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Licence;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.sis.internal.jaxb.gcx.Anchor;
import org.apache.sis.metadata.iso.citation.DefaultOrganisation;
import org.apache.sis.metadata.iso.citation.DefaultResponsibility;
import org.apache.sis.metadata.iso.constraint.DefaultLegalConstraints;
import org.apache.sis.metadata.iso.constraint.DefaultReleasability;
import org.apache.sis.util.iso.DefaultInternationalString;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.constraint.Restriction;
import org.springframework.lang.NonNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All things converting constraint from Theia to ISO-apache SIS
 * package visibility, because it should not be used directly
 */
class ConstraintTransformer {

    /** hiding this constructor */
    private ConstraintTransformer() {}

    /**
     * convert a Theia Constraint to an iso19115 one
     * @param constraint @{@link DataConstraint} containning all theia constrints
     * @return iso 19115 list of @{@link Constraints}
     */
    static List<Constraints> getIsoConstraintsFromConstraint(@NonNull DataConstraint constraint ) {

        List<Constraints> constraintsList = new ArrayList<>();

        // embargo in a specific constraint
        Embargo embargo = constraint.getEmbargo();
        if ( embargo != null )  {
            DefaultReleasability releasability = new DefaultReleasability();
            int days = embargo.getDuration();
            if( days > 0 )                // starting when ?
                releasability.setStatement( new DefaultInternationalString("This dataset is not to be used for "+days+" days" ));

            List<String> allowedUsers = embargo.getPriviledgedUsers();
            if(CollectionUtils.isNotEmpty( allowedUsers ) ) { // because, yes, it can be null...
                releasability.getAddressees().addAll(allowedUsers.stream()
                        .map(u -> new DefaultResponsibility(Role.USER, null, new DefaultOrganisation(u, null, null, null)))
                        .collect(Collectors.toList()));
            }
            DefaultLegalConstraints embargoConstraint = new DefaultLegalConstraints();
            embargoConstraint.setReleasability(releasability);
            constraintsList.add(embargoConstraint);
        }

        // cf mail VCH -> JMB 21/12/2020
        // no limit ??
        DefaultLegalConstraints openBarConstraint = new DefaultLegalConstraints();
        openBarConstraint.getAccessConstraints().add( Restriction.OTHER_RESTRICTIONS ) ;
        openBarConstraint.getOtherConstraints().add( new Anchor(URI.create("http://inspire.ec.europa.eu/metadata-codelist/LimitationsOnPublicAccess/noLimitations"), "no limitations to public access" ) );
        constraintsList.add(openBarConstraint);

        // Licence
        Licence licence = constraint.getLicence();
        DefaultLegalConstraints licenceConstraint = new DefaultLegalConstraints();
        licenceConstraint.getUseConstraints().add(Restriction.OTHER_RESTRICTIONS);
        if ( licence == null ) {
            licenceConstraint.getOtherConstraints().add(
                    new Anchor(URI.create("http://inspire.ec.europa.eu/metadata-codelist/ConditionsApplyingToAccessAndUse/noConditionsApply"), "No conditions apply to access and use" )
            );
        } else {
            licenceConstraint.getUseConstraints().add(Restriction.LICENCE);
            licenceConstraint.getOtherConstraints().add( new DefaultInternationalString(licence.getTitle() + ": " + licence.getUrl()));
        }
        constraintsList.add(licenceConstraint);

        // access Use
        List<I18n> useConstraint = constraint.getAccessUseConstraint();
        DefaultLegalConstraints thanksConstraint = new DefaultLegalConstraints();
        thanksConstraint.getUseLimitations().add( EtlUtils.convertTheiaI18nString( useConstraint ) ) ;
        thanksConstraint.getUseConstraints().add( Restriction.OTHER_RESTRICTIONS);
        thanksConstraint.getOtherConstraints().add(EtlUtils.convertTheiaI18nString(useConstraint));
        constraintsList.add(thanksConstraint);

        // Data Policy
        List<I18n> dataPolicy = constraint.getUrlDataPolicy();
        if ( dataPolicy != null ) {
            DefaultLegalConstraints policyConstraint = new DefaultLegalConstraints();
            policyConstraint.getUseConstraints().add(Restriction.OTHER_RESTRICTIONS);
            policyConstraint.getOtherConstraints().add(EtlUtils.convertTheiaI18nString(dataPolicy));
            constraintsList.add(policyConstraint);
        }

        return constraintsList;
    }
}
