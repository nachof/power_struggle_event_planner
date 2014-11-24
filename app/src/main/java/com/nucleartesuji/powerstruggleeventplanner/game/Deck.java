package com.nucleartesuji.powerstruggleeventplanner.game;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Deck {
	private static Deck instance = null;
	
	public static Deck getInstance() {
		if (instance == null) {
			instance = new Deck();
		}
		return instance;
	}

	private List<Card> standardCards;
	private List<Card> otherCards;

	public void loadCardsData(InputStream dataStream) {
		Loader reader = new Loader(dataStream);
		this.otherCards = reader.readCards();
		this.standardCards = reader.readStandardCards();
	}
	
	public SortableHand getDrawnCards() {
		SortableHand drawnCards = new SortableHand();
		Collections.shuffle(otherCards);
		drawnCards.addAll(otherCards.subList(0, 6));
		drawnCards.addAll(standardCards);
		return drawnCards;
	}
	
	private class Loader {
		Document dom;

		public Loader(InputStream dataStream) {
			try {
				dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(dataStream);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		
		public List<Card> readStandardCards() {
			return readCardsUnderNodeNamed("standard", true);			
		}

		public List<Card> readCards() {
			return readCardsUnderNodeNamed("other", false);
		}

		private List<Card> readCardsUnderNodeNamed(String baseTag, boolean isStandardEvent) {
			Element root = dom.getDocumentElement();
			Element cardsRoot = (Element) root.getElementsByTagName(baseTag).item(0);
			List<Card> result1 = new ArrayList<Card>();
			NodeList items = cardsRoot.getElementsByTagName("card");
			
			for (int i=0;i<items.getLength();i++){
				Node item = items.item(i);
				NodeList properties = item.getChildNodes();
				Card.Builder cardBuilder = Card.getBuilder();
				for (int j=0; j < properties.getLength(); j++) {
					Node property = properties.item(j);
					String name = property.getNodeName().toLowerCase(Locale.getDefault());
					if (name.equals("title")) {
						cardBuilder.setTitle(property.getTextContent());
					} else if (name.equals("text")) {
						cardBuilder.setText(property.getTextContent());
					} else if (name.equals("motivationchange")) {
						cardBuilder.setMotivationChange(property.getTextContent());
					} else if (name.equals("longtext")) {
						cardBuilder.setLongText(property.getTextContent());
					} else {
						// Nothing
					}
				}
				Node cardIdNode;
				if ((cardIdNode = item.getAttributes().getNamedItem("id")) != null){
					cardBuilder.setCardId(cardIdNode.getNodeValue());
                }
				cardBuilder.setStandardEvent(isStandardEvent);
				result1.add(cardBuilder.build());
			}
			List<Card> result = result1;
			return result;
		}
	}

}
