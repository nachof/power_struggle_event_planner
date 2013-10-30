package com.nucleartesuji.powerstruggleeventplanner.game;

import java.util.ArrayList;

public class SortableHand extends ArrayList<Card> {
	private static final long serialVersionUID = 1L;

	public boolean validOrder() {
		if (cardPosition(Card.DIRECTORS_MEETING) < 4 || cardPosition(Card.DIRECTORS_MEETING) < cardPosition(Card.BONUS_PAYMENT)) {
			return false;
		} else {
			return true;
		}
	}

	private int cardPosition(String idToSearch) {
		Card found = null;
		for (Card c: this) {
			if (c.hasCardId(idToSearch)) {
				found = c;
				break;
			}
		}
		return this.indexOf(found);
	}

}