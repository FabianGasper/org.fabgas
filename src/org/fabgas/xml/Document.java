package org.fabgas.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public interface Document extends org.w3c.dom.Document{
	public void saveDocumentToFile(File xmlFile) throws FileNotFoundException, TransformerException;
	public void saveDocumentToFile(File xmlFile,boolean indent) throws FileNotFoundException, TransformerException;
	public String documentToString() throws TransformerException;
	public String documentToString(boolean indent) throws TransformerException;
	public List<Element> getElementsListByTagName(String tagname);
	public List<Element> getElementsListByTagNameNS(String namespaceURI, String localName);
	public List<Element> getChildElementNodes();
	public Document deepCopy() throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, TransformerException ;
}
