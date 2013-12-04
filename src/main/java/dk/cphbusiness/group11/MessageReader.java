/*
 * XML parsing:
 * http://www.vogella.com/articles/JavaXML/article.html
 */
package dk.cphbusiness.group11;

import java.io.ByteArrayInputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

public class MessageReader {

	private String message;
	private int creditScore;

	public MessageReader(String message) {
		this.message = message;
	}

	public void parseCreditScore() throws Exception {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = inputFactory
				.createXMLEventReader(new ByteArrayInputStream(message
						.getBytes()));
		
		boolean lookingForCreditScore = true;
		
		while (eventReader.hasNext() && lookingForCreditScore) {
			XMLEvent event = eventReader.nextEvent();

	        if (event.isStartElement()) {
	          if (event.asStartElement().getName().getLocalPart().equals("creditScore")) {
	        	  creditScore = Integer.parseInt(eventReader.nextEvent().asCharacters().getData());
	        	  lookingForCreditScore = false;
	          }
	        }
		}
	}
}
