package com.example.examplenetworkmelon;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParserHandler extends DefaultHandler {

	StringBuilder content;
	String startName;
	XMLObjectHandler startObject;

	public XMLParserHandler(String startElementName,
			XMLObjectHandler startObject) {
		startName = startElementName;
		this.startObject = startObject;
	}

	Stack<StackItem> mStack = new Stack<StackItem>();

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	int level = 0;

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		level++;
		content = null;

		if (localName.equals(startName)) {
			StackItem item = new StackItem();
			item.handler = startObject;
			item.level = level;
			mStack.push(item);
		}

		if (!mStack.empty()) {
			StackItem item = mStack.peek();
			if (item.level + 1 == level) {
				XMLObjectHandler handler = item.handler.createChildHandler(localName);
				if (handler != null) {
					push(handler);
				}
			}
		}		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		Object value = null;
		if (content != null) {
			value = content.toString();
		}
		if (!mStack.empty()) {
			StackItem item = mStack.peek();
			if (item.level == level) {
				value = mStack.pop().handler;
			}
			if (!mStack.empty()) {
				item = mStack.peek();
				if (item.level + 1 == level) {
					item.handler.setData(localName, value);
				}
			}
		}
		level--;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (content == null) {
			content = new StringBuilder();
		}
		content.append(ch, start, length);
	}

	public void push(XMLObjectHandler handler) {
		StackItem si = new StackItem();
		si.handler = handler;
		si.level = level;
		mStack.push(si);
	}
}
