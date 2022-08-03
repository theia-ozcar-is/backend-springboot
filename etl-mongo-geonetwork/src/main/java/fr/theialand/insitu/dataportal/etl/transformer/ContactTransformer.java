package fr.theialand.insitu.dataportal.etl.transformer;

import fr.theialand.insitu.dataportal.etl.EtlUtils;
import fr.theialand.insitu.dataportal.etl.exception.EtlTransformerException;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactOrganisationRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumContactPersonRoles;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Contact;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Organisation;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.dataset.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.sis.metadata.iso.citation.*;
import org.opengis.metadata.citation.Address;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * All things converting contacs from Theia to ISO-apache SIS
 * package visibility, because it should not be used directly
 */
class ContactTransformer {

    /** hiding this constructor */
    private ContactTransformer() {}

    private static final Logger LOG = LoggerFactory.getLogger(ContactTransformer.class);

    /**
     * method to convert a Theia contact to an ISO Responsibly
     * some info can't fit in ISO definition, and therefore is lost in the process
     *
     * Metadata contacts:
     *  - pointOfContact = OZCAR RI (producer) + producer mail or project leader mail
     *  - principaleInvestigator
     *  - dataManager
     *
     * In Pivot model, a person has Orgs childs
     * In ISO model ,  a org has Person childs
     * so this method may reverse the owning order
     * @param datasetContacts , extending Person or Org
     * @param producerContacts, only @{@link Person}s
     * @param producerOrg the default Org to use
     * @return list of either a {@link DefaultIndividual} or a {@link DefaultOrganisation}
     */
    static List<Responsibility> getContactsFromMetadata(
            @NonNull Collection<Contact> datasetContacts,
            @NonNull Collection<Person> producerContacts,
            @NonNull DefaultOrganisation producerOrg,
            String producerMail) throws EtlTransformerException {

        List<Responsibility> responsibilities = new ArrayList<>();

        LOG.info("Converting {} contacts from Pivot Format to SIS iso-19115", datasetContacts.size());
        for( Contact contact: datasetContacts ) {

            //-------------------------
            //Metadata contacts:
            // - pointOfContact = OZCAR RI (producer) + producer mail or project leader mail
            // - principaleInvestigator
            // - dataManager
            //Organisation.email is set with the producer.email if existing or with the first project leader mail.
            // see issue #10
//            if (contact instanceof Organisation) {
//                Organisation orgContact = (Organisation) contact;
//                EnumContactOrganisationRoles orgaRole = EnumContactOrganisationRoles.getEnum(orgContact.getRole());
//
//                DefaultContact pocContact = new DefaultContact();
//                if (producerMail != null) {
//                    Address pocMail = new DefaultAddress();
//                    pocMail.getElectronicMailAddresses().add(producerMail);
//                    pocContact.setAddresses(Collections.singleton(pocMail));
//                } else {
//                    pocContact = extractPersonContact(producerContacts, EnumContactPersonRoles.PROJECT_LEADER);
//                }
//                //pointOfContact
//                responsibilities.add(new DefaultResponsibility(
//                        getRoleFromOrganisationRole(orgaRole),
//                        null, // Extent, spatial or temporal, for this role
//                        getIsoOrganisationFromOrganisation(orgContact, pocContact )));
//
//            } else
                if (contact instanceof Person) {
                Person personContact = (Person) contact;
                EnumContactPersonRoles personRole = EnumContactPersonRoles.getEnum(personContact.getRole());

                if (personRole == EnumContactPersonRoles.DATA_MANAGER ||personRole == EnumContactPersonRoles.PRINCIPAL_INVESTIGATOR ) {



                DefaultIndividual isoPerson = getIsoIndividualFromPerson(personContact);
                DefaultResponsibility responsibility = new DefaultResponsibility();
                responsibility.setRole(getRoleFromPersonRole(personRole));

                Organisation orga = personContact.getOrganisation();
                if (orga != null) {
                    EnumContactOrganisationRoles orgaRole = EnumContactOrganisationRoles.getEnum(orga.getRole());
                    DefaultOrganisation isoOrga = getIsoOrganisationFromOrganisation(orga,null);
                    isoOrga.getContactInfo().addAll(isoPerson.getContactInfo());
                    isoOrga.getIndividual().add(isoPerson);
                    responsibility.getParties().add(isoOrga);
                } else {
                    // inspire require an organizationName for each contact. see issue  #20
                    // so we add the producer lab to the parties so it can get later transfromed into an organisationName
                    // then we set the mail of this Org to the mail of the person to override it ...
                    producerOrg.getContactInfo().clear();
                    producerOrg.getContactInfo().addAll(isoPerson.getContactInfo());
                    responsibility.getParties().add(isoPerson);
                    responsibility.getParties().add(producerOrg);
                }

                responsibilities.add(responsibility);

                }
            } else if (!(contact instanceof Organisation)) {
                throw new EtlTransformerException(contact + " not of type Person or Organisation : " + contact.getClass().getSimpleName());
            }
        }
        return responsibilities;
    }

    /**
     * method to convert a Theia contact to an ISO Responsibly
     * some info can't fit in ISO definition, and therefore is lost in the process
     *
     * Data contacts: all the contacts from the dataset.contacts
     *
     * In Pivot model, a person has Orgs childs
     * In ISO model ,  a org has Person childs
     * so this method may reverse the owning order
     * @param datasetContacts , extending Person or Org
     * @param producerContacts, only @{@link Person}s
     * @param producerOrg the default Org to use
     * @return list of either a {@link DefaultIndividual} or a {@link DefaultOrganisation}
     */
    static List<Responsibility> getContactsFromData(
            @NonNull Collection<Contact> datasetContacts,
            @NonNull Collection<Person> producerContacts,
            @NonNull DefaultOrganisation producerOrg,
            String producerMail) throws EtlTransformerException {

        List<Responsibility> responsibilities = new ArrayList<>();

        LOG.info("Converting {} contacts from Pivot Format to SIS iso-19115", datasetContacts.size());
        for( Contact contact: datasetContacts ) {

            //Organisation.email is set with the producer.email if existing or with the first project leader mail.
            // see issue #10
            if (contact instanceof Organisation) {
                Organisation orgContact = (Organisation) contact;
                EnumContactOrganisationRoles orgaRole = EnumContactOrganisationRoles.getEnum(orgContact.getRole());

                DefaultContact pocContact = new DefaultContact();
                if (producerMail != null) {
                    Address pocMail = new DefaultAddress();
                    pocMail.getElectronicMailAddresses().add(producerMail);
                    pocContact.setAddresses(Collections.singleton(pocMail));
                } else {
                    pocContact = extractPersonContact(producerContacts, EnumContactPersonRoles.PROJECT_LEADER);
                }
                //pointOfContact
                responsibilities.add(new DefaultResponsibility(
                        getRoleFromOrganisationRole(orgaRole),
                        null, // Extent, spatial or temporal, for this role
                        getIsoOrganisationFromOrganisation(orgContact, pocContact )));

            } else if (contact instanceof Person) {
                Person personContact = (Person) contact;
                EnumContactPersonRoles personRole = EnumContactPersonRoles.getEnum(personContact.getRole());

                    DefaultIndividual isoPerson = getIsoIndividualFromPerson(personContact);
                    DefaultResponsibility responsibility = new DefaultResponsibility();
                    responsibility.setRole(getRoleFromPersonRole(personRole));

                    Organisation orga = personContact.getOrganisation();
                    if (orga != null) {
                        EnumContactOrganisationRoles orgaRole = EnumContactOrganisationRoles.getEnum(orga.getRole());
                        DefaultOrganisation isoOrga = getIsoOrganisationFromOrganisation(orga,null);
                        isoOrga.getContactInfo().addAll(isoPerson.getContactInfo());
                        isoOrga.getIndividual().add(isoPerson);
                        responsibility.getParties().add(isoOrga);
                    } else {
                        // inspire require an organizationName for each contact. see issue  #20
                        // so we add the producer lab to the parties so it can get later transfromed into an organisationName
                        // then we set the mail of this Org to the mail of the person to override it ...
                        producerOrg.getContactInfo().clear();
                        producerOrg.getContactInfo().addAll(isoPerson.getContactInfo());
                        responsibility.getParties().add(isoPerson);
                        responsibility.getParties().add(producerOrg);
                    }

                    responsibilities.add(responsibility);

            } else {
                throw new EtlTransformerException(contact + " not of type Person or Organisation : " + contact.getClass().getSimpleName());
            }
        }
        return responsibilities;
    }

    /**
     * Get the contact matchiing the given role.
     * @param contacts bunch of @{@link Contact} to search
     * @param personRole the maching role we are looking for
     * @return iso19115 @{@link org.opengis.metadata.citation.Contact}
     */
    static DefaultContact extractPersonContact(Collection<? extends Contact> contacts, @NonNull EnumContactPersonRoles personRole) throws EtlTransformerException {
        Person person = (Person) contacts.stream().filter(c -> c instanceof Person
                && EnumContactPersonRoles.getEnum(((Person) c).getRole()).equals(personRole))
                .findFirst().orElseThrow(() -> new EtlTransformerException("nobody with role " + personRole + " found"));

        DefaultContact pocContactInfo = new DefaultContact();
        Address pocMail = new DefaultAddress();
        pocMail.getElectronicMailAddresses().add(person.getEmail());
        pocContactInfo.setAddresses(Collections.singleton(pocMail));

        return pocContactInfo;

    }


    // INNER METHODS

    /**
     * convert a Theia @{@link Organisation} to a iso19115 {@link org.opengis.metadata.citation.Organisation}
     * @param orgContact in the Theia OZCAR understanding
     * @param projectLeaderContact needed to create a valid INSPIRE organisation
     * @return DefaultOrganisation in the Apache SIS / ISO understanding
     */
    private static DefaultOrganisation getIsoOrganisationFromOrganisation(Organisation orgContact,DefaultContact projectLeaderContact) {
        String enName = EtlUtils.getENorAnyStringFromI18n(orgContact.getName());
        // in our pivot, orgs don't have mails, nor persons.

        return new DefaultOrganisation( enName, null, null, projectLeaderContact );
    }

    /**
     * convert an theia @{@link Person} to an iso19115 @{@link org.opengis.metadata.citation.Individual}
     * @param personContact n the Theia OZCAR understanding
     * @return DefaultIndividual in the Apache SIS / ISO understanding
     */
    private static DefaultIndividual getIsoIndividualFromPerson(Person personContact)  {

        DefaultContact isoContact = new DefaultContact();

        if ( StringUtils.isNotEmpty( personContact.getEmail() )) {
            Address emailAddress = new DefaultAddress();
            emailAddress.getElectronicMailAddresses().add(personContact.getEmail());
            isoContact.setAddresses( Collections.singleton( emailAddress ) );
        }

        return new DefaultIndividual(personContact.getFirstName()+" "+personContact.getLastName(), personContact.getRole(), isoContact );
    }



    /**
     * Enum converter
     * @param personRole in the Theia OZCAR understanding
     * @return Role in the Apache SIS / ISO understanding
     */
    private static Role getRoleFromPersonRole( EnumContactPersonRoles personRole ) throws EtlTransformerException {
        switch (personRole) {
            case DATA_MANAGER:           return Role.CUSTODIAN ;
            case DATA_COLLECTOR:         return Role.ORIGINATOR ;
            case PROJECT_LEADER:         return Role.POINT_OF_CONTACT ;
            case PROJECT_MEMBER:         return Role.ORIGINATOR ;
            case PRINCIPAL_INVESTIGATOR: return Role.PRINCIPAL_INVESTIGATOR;
            default: throw new EtlTransformerException(personRole.toString()+" undefined");
        }
    }

    /**
     * Enum converter
     * @param orgaRole in the Theia OZCAR understanding
     * @return Role in the Apache SIS / ISO understanding
     */
    private static Role getRoleFromOrganisationRole( EnumContactOrganisationRoles orgaRole ) throws EtlTransformerException {
        switch (orgaRole) {
            case RESEARCH_GROUP:           return Role.ORIGINATOR ;
            // no others for now.
            default: throw new EtlTransformerException(orgaRole.toString()+" undefined");
        }
    }
}
