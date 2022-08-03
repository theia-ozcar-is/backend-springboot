package fr.theialand.insitu.dataportal.etl.exception;

import javax.xml.bind.JAXBException;

/**
 * When SIS couldn't gennerate some XML
 * @deprecated moved to GN client
 */
@Deprecated
public class EtlXmlMarshallingException extends EtlException {

    public EtlXmlMarshallingException(String msg, JAXBException xmlEx) {
        super(msg, xmlEx);
    }

    public EtlXmlMarshallingException(JAXBException jaxbEx) {
        super(jaxbEx);
    }
}
