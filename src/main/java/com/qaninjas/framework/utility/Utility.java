package com.qaninjas.framework.utility;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class Utility {

	private static  Logger logger = Logger.getLogger(Utility.class);
	
	public boolean includeGroupXML(String filePath, String group) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			Node groupNode = doc.getElementsByTagName("include").item(0);
			NamedNodeMap attr = groupNode.getAttributes();
			Node nodeAttr = attr.getNamedItem("name");
			nodeAttr.setTextContent(group);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource();
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			return true;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			logger.error(pce.getMessage());
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			logger.error(tfe.getMessage());
			return false;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.error(ioe.getMessage());
			return false;
		} catch (SAXException sae) {
			sae.printStackTrace();
			logger.error(sae.getMessage());
			return false;
		}
	}
}
