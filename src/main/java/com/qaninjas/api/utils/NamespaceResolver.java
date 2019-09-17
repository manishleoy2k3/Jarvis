package com.qaninjas.api.utils;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.Document;

public class NamespaceResolver implements NamespaceContext {

	private Document sourceDocument;
	
	public NamespaceResolver(Document document) {
		sourceDocument = document;
	}

	public String getNamespaceURI(String prefix) {
		if(prefix.equals(XMLConstants.DEFAULT_NS_PREFIX)){
			return sourceDocument.lookupNamespaceURI(null);
		}else {
			return sourceDocument.lookupNamespaceURI(prefix);
		}
	}

	public String getPrefix(String namespaceURI) {
		return sourceDocument.lookupPrefix(namespaceURI);
	}

	public Iterator<String> getPrefixes(String namespaceURI) {
		ArrayList<String> nodeValue = new ArrayList<String>();
		return nodeValue.iterator();
	}

}
