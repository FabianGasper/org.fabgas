package org.fabgas.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

public class DocumentImpl implements Document{
	
	org.w3c.dom.Document d;
	
	public DocumentImpl(String xml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();			
		StringBuilder xmlStringBuilder = new StringBuilder().append(xml);
		this.d = builder.parse(new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8")));
	}
	
	public DocumentImpl(File xmlFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();	
		this.d = builder.parse(xmlFile);
    }
	
	public DocumentImpl(org.w3c.dom.Document doc) {	
		this.d = doc;
    }
	
	public DocumentImpl(Document doc) throws TransformerException, ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {	
		this(doc.documentToString());
    }
	
	@Override
	public Document deepCopy() throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, TransformerException {
		return new DocumentImpl(this);
	}
	
	@Override
	public void saveDocumentToFile(File xmlFile) throws FileNotFoundException, TransformerException{
		saveDocumentToFile(xmlFile,false);
	}
	
	@Override
	public void saveDocumentToFile(File xmlFile,boolean indent) throws FileNotFoundException, TransformerException{
		TransformerFactory tf = TransformerFactory.newInstance();
		if(indent)tf.setAttribute("indent-number", new Integer(2));
	    Transformer transformer= tf.newTransformer();       
	    if(indent)transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        FileOutputStream outStream = new FileOutputStream(xmlFile);
        transformer.transform(new DOMSource(d), new StreamResult(outStream));
    }
	
	@Override
	public String documentToString() throws TransformerException{
		return documentToString(false);
	}
	@Override
	public String documentToString(boolean indent) throws TransformerException{
		TransformerFactory tf = TransformerFactory.newInstance();
		if(indent)tf.setAttribute("indent-number", new Integer(2));
	    Transformer transformer= tf.newTransformer();
	    if(indent)transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(d), new StreamResult(writer));    
	    return writer.getBuffer().toString();
    }
	
	@Override
	public List<Element> getElementsListByTagName(String tagname) {
		List<Element> list = new ArrayList<>();
		NodeList nl = d.getElementsByTagName(tagname);
		for(int i=0;i<nl.getLength();i++) {
			Node childNode=nl.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)list.add((Element)childNode);
		}
		return list;
	}

	@Override
	public List<Element> getElementsListByTagNameNS(String namespaceURI, String localName) {
		List<Element> list = new ArrayList<>();
		NodeList nl = d.getElementsByTagNameNS(namespaceURI, localName);
		for(int i=0;i<nl.getLength();i++) {
			Node childNode=nl.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)list.add((Element)childNode);
		}
		return list;
	}
	
	@Override
	public List<Element> getChildElementNodes() {
		List<Element> list = new ArrayList<>();
		NodeList nl = d.getChildNodes();
		for(int i=0;i<nl.getLength();i++) {
			Node childNode=nl.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE)list.add((Element)childNode);
		}
		return list;
	}
	
	
	
	
	
	@Override
	public Node adoptNode(Node source) throws DOMException {
		return d.adoptNode(source);
	}

	@Override
	public Attr createAttribute(String name) throws DOMException {
		return d.createAttribute(name);
	}

	@Override
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
		return d.createAttributeNS( namespaceURI,  qualifiedName);
	}

	@Override
	public CDATASection createCDATASection(String data) throws DOMException {
		return d.createCDATASection(data);
	}

	@Override
	public Comment createComment(String data) {
		return d.createComment(data);
	}

	@Override
	public DocumentFragment createDocumentFragment() {
		return d.createDocumentFragment();
	}

	@Override
	public Element createElement(String tagName) throws DOMException {
		return d.createElement(tagName);
	}

	@Override
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		return d.createElementNS(namespaceURI, qualifiedName);
	}

	@Override
	public EntityReference createEntityReference(String name) throws DOMException {
		return d.createEntityReference(name);
	}

	@Override
	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
		return d.createProcessingInstruction(target, data);
	}

	@Override
	public Text createTextNode(String data) {
		return d.createTextNode(data);
	}

	@Override
	public DocumentType getDoctype() {
		return d.getDoctype();
	}

	@Override
	public Element getDocumentElement() {
		return d.getDocumentElement();
	}

	@Override
	public String getDocumentURI() {
		return d.getDocumentURI();
	}

	@Override
	public DOMConfiguration getDomConfig() {
		return d.getDomConfig();
	}

	@Override
	public Element getElementById(String elementId) {
		return d.getElementById(elementId);
	}

	@Override
	public NodeList getElementsByTagName(String tagname) {
		return d.getElementsByTagName(tagname);
	}

	@Override
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		return d.getElementsByTagNameNS(namespaceURI, localName);
	}

	@Override
	public DOMImplementation getImplementation() {
		return d.getImplementation();
	}

	@Override
	public String getInputEncoding() {
		return d.getInputEncoding() ;
	}

	@Override
	public boolean getStrictErrorChecking() {
		return d.getStrictErrorChecking();
	}

	@Override
	public String getXmlEncoding() {
		return d.getXmlEncoding();
	}

	@Override
	public boolean getXmlStandalone() {
		return d.getXmlStandalone();
	}

	@Override
	public String getXmlVersion() {
		return d.getXmlVersion();
	}

	@Override
	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		return d.importNode(importedNode, deep);
	}

	@Override
	public void normalizeDocument() {
		d.normalize();
	}

	@Override
	public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
		return d.renameNode(n, namespaceURI, qualifiedName);
	}

	@Override
	public void setDocumentURI(String documentURI) {
		d.setDocumentURI(documentURI);
	}

	@Override
	public void setStrictErrorChecking(boolean strictErrorChecking) {
		d.setStrictErrorChecking(strictErrorChecking);
	}

	@Override
	public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
		d.setXmlStandalone(xmlStandalone);
	}

	@Override
	public void setXmlVersion(String xmlVersion) throws DOMException {
		d.setXmlVersion(xmlVersion);
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		return d.appendChild(newChild);
	}

	@Override
	public Node cloneNode(boolean deep) {
		return d.cloneNode(deep);
	}

	@Override
	public short compareDocumentPosition(Node other) throws DOMException {
		return compareDocumentPosition(other);
	}

	@Override
	public NamedNodeMap getAttributes() {
		return d.getAttributes();
	}

	@Override
	public String getBaseURI() {
		return d.getBaseURI();
	}

	@Override
	public NodeList getChildNodes() {
		return d.getChildNodes();
	}

	@Override
	public Object getFeature(String feature, String version) {
		return d.getFeature(feature, version);
	}

	@Override
	public Node getFirstChild() {
		return d.getFirstChild();
	}

	@Override
	public Node getLastChild() {
		return d.getLastChild();
	}

	@Override
	public String getLocalName() {
		return d.getLocalName();
	}

	@Override
	public String getNamespaceURI() {
		return d.getNamespaceURI();
	}

	@Override
	public Node getNextSibling() {
		return d.getNextSibling();
	}

	@Override
	public String getNodeName() {
		return d.getNodeName();
	}

	@Override
	public short getNodeType() {
		return d.getNodeType();
	}

	@Override
	public String getNodeValue() throws DOMException {
		return d.getNodeValue();
	}

	@Override
	public org.w3c.dom.Document getOwnerDocument() {
		return d.getOwnerDocument();
	}

	@Override
	public Node getParentNode() {
		return d.getParentNode();
	}

	@Override
	public String getPrefix() {
		return d.getPrefix();
	}

	@Override
	public Node getPreviousSibling() {
		return d.getPreviousSibling();
	}

	@Override
	public String getTextContent() throws DOMException {
		return d.getTextContent();
	}

	@Override
	public Object getUserData(String key) {
		return d.getUserData(key);
	}

	@Override
	public boolean hasAttributes() {
		return d.hasAttributes();
	}

	@Override
	public boolean hasChildNodes() {
		return d.hasChildNodes();
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		return d.insertBefore(newChild, refChild);
	}

	@Override
	public boolean isDefaultNamespace(String namespaceURI) {
		return d.isDefaultNamespace(namespaceURI);
	}

	@Override
	public boolean isEqualNode(Node arg) {
		return d.isEqualNode(arg);
	}

	@Override
	public boolean isSameNode(Node other) {
		return d.isSameNode(other);
	}

	@Override
	public boolean isSupported(String feature, String version) {
		return d.isSupported(feature, version);
	}

	@Override
	public String lookupNamespaceURI(String prefix) {
		return d.lookupNamespaceURI(prefix);
	}

	@Override
	public String lookupPrefix(String namespaceURI) {
		return d.lookupPrefix(namespaceURI);
	}

	@Override
	public void normalize() {
		d.normalize();
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {
		return d.removeChild(oldChild);
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		return d.replaceChild(newChild, oldChild);
	}

	@Override
	public void setNodeValue(String nodeValue) throws DOMException {
		d.setNodeValue(nodeValue);
	}

	@Override
	public void setPrefix(String prefix) throws DOMException {
		d.setPrefix(prefix);
	}

	@Override
	public void setTextContent(String textContent) throws DOMException {
		d.setTextContent(textContent);
	}

	@Override
	public Object setUserData(String key, Object data, UserDataHandler handler) {
		return d.setUserData(key, data, handler);
	}

}
