package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactOrganisationRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Contact;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Organisation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Person;
import org.apache.sis.metadata.iso.citation.DefaultOrganisation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opengis.metadata.citation.Responsibility;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
class ContactTransformerTest {

    @DisplayName("issue #35, When building MD contacts, should not include producer mail")
    @Test
    void buildingContactWithFullPersonsShouldNotIncludeMailFromProducer() throws EtlTransformerException {

        // 1. setup
        Organisation orga = new Organisation();
        orga.setRole(EnumContactOrganisationRoles.RESEARCH_GROUP.toString());
        orga.setAcronym("TEST");
        orga.setName(EtlUtils.createSimpleI18nString("organame"));

        Person pi = new Person();
        pi.setRole(EnumContactPersonRoles.PRINCIPAL_INVESTIGATOR.toString());
        pi.setEmail("pi@pi.pi");
        pi.setFirstName("piFirst");
        pi.setLastName("piLast");
        pi.setOrganisation(orga);

        Person dm = new Person();
        dm.setRole(EnumContactPersonRoles.DATA_MANAGER.toString());
        dm.setEmail("dm@dm.dm");
        dm.setFirstName("dmFirst");
        dm.setLastName("dmLast");
        dm.setOrganisation(orga);

        Person pl = new Person();
        pl.setRole(EnumContactPersonRoles.PROJECT_LEADER.toString());
        pl.setEmail("pl@pl.pl");
        pl.setFirstName("plFirst");
        pl.setLastName("plLast");
        pl.setOrganisation(orga);

        List<Contact> dsContacts = List.of(pi, dm);
        List<Person>  producerPersons = List.of(pl);

        DefaultOrganisation isoOrga = new DefaultOrganisation("testOrg",null,null,null);

        // 2 . run
        List<Responsibility> resps = ContactTransformer.getContactsFromMetadata( dsContacts, producerPersons, isoOrga, null);

        // 3. assert
        for( Responsibility resp : resps ){
            // seriously ?
            String mail = resp.getParties().iterator().next()
                    .getContactInfo().iterator().next()
                    .getAddresses().iterator().next()
                    .getElectronicMailAddresses().iterator().next();

            switch(resp.getRole().name()) {
                case "PRINCIPAL_INVESTIGATOR":
                    assertThat(mail).isEqualTo("pi@pi.pi");
                    break;
                case "CUSTODIAN":
                    assertThat(mail).isEqualTo("dm@dm.dm");
                    break;
                default:
            }
        }

    }

    /**
     *  issue #35
     */
    @DisplayName("issue #35, When building MD contacts, should not include producer mail even if the contacts does not have Organisation")
    @Test
    void buildingContactWithPartialPersonsShouldNotIncludeMailFromProducer() throws EtlTransformerException {

        // 1. setup
        Organisation orga = new Organisation();
        orga.setRole(EnumContactOrganisationRoles.RESEARCH_GROUP.toString());
        orga.setAcronym("TEST");
        orga.setName(EtlUtils.createSimpleI18nString("organame"));

        Person pi = new Person();
        pi.setRole(EnumContactPersonRoles.PRINCIPAL_INVESTIGATOR.toString());
        pi.setEmail("pi@pi.pi");
        pi.setFirstName("piFirst");
        pi.setLastName("piLast");
        // it has no org
        //        pi.setOrganisation(orga);

        Person dm = new Person();
        dm.setRole(EnumContactPersonRoles.DATA_MANAGER.toString());
        dm.setEmail("dm@dm.dm");
        dm.setFirstName("dmFirst");
        dm.setLastName("dmLast");
        // it has no org
        //        dm.setOrganisation(orga);

        Person pl = new Person();
        pl.setRole(EnumContactPersonRoles.PROJECT_LEADER.toString());
        pl.setEmail("pl@pl.pl");
        pl.setFirstName("plFirst");
        pl.setLastName("plLast");
        pl.setOrganisation(orga);

        List<Contact> dsContacts = List.of(pi, dm);
        List<Person>  producerPersons = List.of(pl);

        DefaultOrganisation isoOrga = new DefaultOrganisation("testOrg",null,null,null);

        // 2 . run
        List<Responsibility> resps = ContactTransformer.getContactsFromMetadata( dsContacts, producerPersons, isoOrga, null);

        // 3. assert
        for( Responsibility resp : resps ){
            // seriously ?
            String mail = resp.getParties().iterator().next()
                    .getContactInfo().iterator().next()
                    .getAddresses().iterator().next()
                    .getElectronicMailAddresses().iterator().next();

            switch(resp.getRole().name()) {
                case "PRINCIPAL_INVESTIGATOR":
                    assertThat(mail).isEqualTo("pi@pi.pi");
                    break;
                case "CUSTODIAN":
                    assertThat(mail).isEqualTo("dm@dm.dm");
                    break;
                default:
            }
        }

    }
}
