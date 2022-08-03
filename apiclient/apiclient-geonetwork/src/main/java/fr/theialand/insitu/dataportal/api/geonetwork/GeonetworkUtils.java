package fr.theialand.insitu.dataportal.api.geonetwork;

import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkXMLMarshallingException;
import fr.theialand.insitu.dataportal.api.geonetwork.exceptions.GeonetworkXMLUnmarshallingException;
import fr.theialand.insitu.dataportal.api.geonetwork.model.InfoReport;
import fr.theialand.insitu.dataportal.api.geonetwork.model.Report;
import fr.theialand.insitu.dataportal.api.geonetwork.model.SimpleMetadataProcessingReport;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Various tools
 */
public class GeonetworkUtils {

    private static final Logger LOG = LoggerFactory.getLogger(GeonetworkUtils.class);

    /** masking constructor, utility class */
    private GeonetworkUtils() {}

    /**
     * Very basic handling of the Report object that is regurlarly returned by GN
     * on various ops
     * For now, only logs some of the info it find in the report
     * @return  @{@link UUID} of the operation
     */
    static UUID analyzeReport(@NonNull SimpleMetadataProcessingReport report ){
        for( Report errorReport : ListUtils.emptyIfNull(report.getErrors()))
            LOG.warn("Error reported: {}",errorReport.getMessage());

        for( InfoReport infoReport : ListUtils.emptyIfNull(report.getInfos()))
            LOG.info("Info reported: {}",infoReport.getMessage());

        // No doc on what these codes means
        for(Map.Entry<String, List<Report>> metadataError :  MapUtils.emptyIfNull(report.getMetadataErrors()).entrySet() )
            LOG.warn("MetadataError: code {}, {}",
                    metadataError.getKey(),
                    metadataError.getValue().stream().map(Report::getMessage).collect(Collectors.toList()) );

        // No doc on what these codes means
        for(Map.Entry<String, List<InfoReport>> metadataInfo :  MapUtils.emptyIfNull(report.getMetadataInfos()).entrySet() )
            LOG.info("MetadataInfo: code {}, {}",
                    metadataInfo.getKey(),
                    metadataInfo.getValue().stream().map(InfoReport::getMessage).collect(Collectors.toList()) );

        if( report.getUuid() != null )
            return UUID.fromString(report.getUuid());
        else
            return null ;
    }

    /**
     * Do the marshalling into XML here
     * WARNING, it does some fiddling with the XML as well ! (to correct a few bugs and inspire weirdness)
     * @param sisMetadata apache SIS MD
     * @return xml as String
     * @throws GeonetworkXMLMarshallingException something went wrong
     */
    public static String toXml(@NonNull Metadata sisMetadata) throws GeonetworkXMLMarshallingException {

        // This is the XML tree , much like JsonNode for example
        DOMResult xmlDom ;
        try {
            xmlDom = new DOMResult(DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().newDocument());
            LOG.info("XML serialization ( marshalling ) of ISO-19115 metadata with Identifier {}", sisMetadata.getMetadataIdentifier());

            // marshal the MD document into an XML tree of type DOMResult  , using old standards iso19115-2007 (19139)
            XML.marshal(sisMetadata,xmlDom, Map.of(XML.METADATA_VERSION, "2007"));

        } catch (JAXBException | ParserConfigurationException jaxbEx ) {
            throw new GeonetworkXMLMarshallingException("Problem encountered when parsing Object Metadata into XML Node", jaxbEx);
        }

        // correcting the XML output to overcome various more or les official issues.
        removeDatasetURI(          xmlDom );
        replaceLanguageCodeURI(    xmlDom );
        replaceMDscopeWithDQscope( xmlDom );
        insertDistributionFormatVersionTag(xmlDom);

        // string output
        ByteArrayOutputStream xmlOut = new ByteArrayOutputStream();
        try {
            Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
            transformer.transform(new DOMSource(xmlDom.getNode()), new StreamResult(xmlOut));
        } catch ( TransformerException transEx) {
            throw new GeonetworkXMLMarshallingException("Problem encountered when parsing Object Metadata into a string", transEx);
        }
        String xml = xmlOut.toString(StandardCharsets.UTF_8);
        LOG.debug("Xml generated from Metadata: {}", xml);
        return xml;
    }

    /**
     * Do the marshalling from  XML here
     */
    public static Metadata fromXml(@NonNull String xml) throws GeonetworkXMLUnmarshallingException {
        try {
            LOG.debug("XML deserialization ( unmarshalling ) of ISO-19115 XML STRING");
            Object obj = XML.unmarshal(xml);
            return (Metadata) obj;
        } catch ( JAXBException jaxbEx ) {
            throw new GeonetworkXMLUnmarshallingException("Problem encountered when deserializing XML string into Object, into Metadata", jaxbEx);
        }
    }

    /**
     * DataSetURI seems deprecated, but SIS stills output it when configured with legacy iso19139 option :=> we remove it
     * @param xmlDom XML dom to modify
     * @throws GeonetworkXMLMarshallingException if xpath or other Ex
     */
    private static void removeDatasetURI(DOMResult xmlDom) throws GeonetworkXMLMarshallingException {
        LOG.info("issue #11: removing datasetURI XML node, as it should not be generated for iso19139");
        XPath xPath = XPathFactory.newDefaultInstance().newXPath();
        try {
            XPathExpression expression = xPath.compile("//*[local-name()='dataSetURI']");
            Node datasetUriNode = (Node) expression.evaluate(xmlDom.getNode(), XPathConstants.NODE);
            if ( datasetUriNode != null )
                datasetUriNode.getParentNode().removeChild(datasetUriNode);
        } catch (XPathExpressionException xpathEx) {
            throw new GeonetworkXMLMarshallingException("issue #11: removing datasetURI XML node is not possible", xpathEx);
        }
    }

    /**
     * LanguageCode is hardcoded in @{@link org.apache.sis.internal.jaxb.cat.CodeListUID} when using legacy 19139 option
     * Inspire test suite requires another URL
     * @param xmlDom XML dom to modify
     * @throws GeonetworkXMLMarshallingException if xpath or other Ex
     */
    private static void replaceLanguageCodeURI(DOMResult xmlDom) throws GeonetworkXMLMarshallingException {
        LOG.info("issue #8: changing LanguageCode codeList, to match the one expected by Inspire Test Suite");
        XPath xPath = XPathFactory.newDefaultInstance().newXPath();
        try {
            XPathExpression expression = xPath.compile("//*[local-name()='LanguageCode']");
            NodeList langCodeNodes = (NodeList) expression.evaluate(xmlDom.getNode(), XPathConstants.NODESET);
            for( int i = 0 ; i < langCodeNodes.getLength() ; i++ ) {
                Node langCodeNode = langCodeNodes.item(i);
                langCodeNode.getAttributes().getNamedItem("codeList").setNodeValue("http://www.loc.gov/standards/iso639-2/");
            }
        } catch (XPathExpressionException xpathEx) {
            throw new GeonetworkXMLMarshallingException("issue #8: correcting LanguageCode codeList is not possible", xpathEx);
        }
    }

    /**
     * https://issues.apache.org/jira/browse/SIS-508
     * https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/issues/7
     *
     * SIS does not generate correct xml tag when outputing legacy 19139
     * @param xmlDom XML dom to modify
     * @throws GeonetworkXMLMarshallingException if xpath or other Ex
     */
    private static void replaceMDscopeWithDQscope(DOMResult xmlDom) throws GeonetworkXMLMarshallingException {
        LOG.info("issue #7 and #18 and #SIS-508: replacing MD_Scope with DQ_Scope");
        XPath xPath = XPathFactory.newDefaultInstance().newXPath();
        try {
            // this "local-names" thing allows to match all NS
            XPathExpression expression = xPath.compile("//*[local-name()='DQ_DataQuality']/*[local-name()='scope']/*[local-name()='MD_Scope']");
            NodeList scopeNodes = (NodeList) expression.evaluate(xmlDom.getNode(), XPathConstants.NODESET);
            for( int i = 0 ; i < scopeNodes.getLength() ; i++ ) {
                Node scopeNode = scopeNodes.item(i);
                String ns = scopeNode.getNamespaceURI();  // NS handling is quite the trouble. easier to do it with "prefix"
                ( (Document) xmlDom.getNode() ).renameNode(scopeNode, ns, "DQ_Scope").setPrefix("gmd");
            }
        } catch (XPathExpressionException xpathEx) {
            throw new GeonetworkXMLMarshallingException("issue #7: correcting MD_Scope is not possible", xpathEx);
        }
    }

    /**
     * https://gricad-gitlab.univ-grenoble-alpes.fr/theia-ozcar/backend-springboot/-/issues/24
     *
     * SIS does not generate correct xml tag when outputing legacy 19139
     * the <Version></Version> Tag, deprecated in iso1915-3:201X, is not honored by SIS.
     * its replacement, is not converted to 19139.
     * @param xmlDom XML dom to modify
     * @throws GeonetworkXMLMarshallingException if xpath or other Ex
     */
    private static void insertDistributionFormatVersionTag(DOMResult xmlDom) throws GeonetworkXMLMarshallingException {
        LOG.info("issue #24 : Setting up the distributon/Version tag correctly, to nilReason=missing");
        XPath xPath = XPathFactory.newDefaultInstance().newXPath();
        try {
            XPathExpression expression = xPath.compile("//*[local-name()='distributionFormat']/*[local-name()='MD_Format']");
            Node distriFormatNode = (Node) expression.evaluate(xmlDom.getNode(), XPathConstants.NODE);
            Document doc = (Document) xmlDom.getNode();
            Element  version = doc.createElementNS("http://www.isotc211.org/2005/gmd","gmd:version");
            version.setAttributeNS("http://www.isotc211.org/2005/gco","gco:nilReason","missing");
            distriFormatNode.appendChild(version);
        } catch (XPathExpressionException xpathEx) {
            throw new GeonetworkXMLMarshallingException("issue #24: correcting MD_Scope is not possible", xpathEx);
        }
    }
}
