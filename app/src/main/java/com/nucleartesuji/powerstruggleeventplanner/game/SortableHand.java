package com.nucleartesuji.powerstruggleeventplanner.game;

import java.util.ArrayList;

public class SortableHand extends ArrayList<Card> {
    private static final long serialVersionUID = 1L;

    public boolean validOrder() {
        if (getCardPosition(Card.DIRECTORS_MEETING) < 4 || getCardPosition(Card.DIRECTORS_MEETING) < getCardPosition(Card.BONUS_PAYMENT)) {
            return false;
        } else {
            return true;
        }
    }

    public void moveCardUp(int position) {
        if (position > 0) {
            Card item = get(position);
            set(position, get(position - 1));
            set(position - 1, item);
        }
    }

    public void moveCardDown(int position) {
        if (position < size() - 1) {
            Card item = get(position);
            set(position, get(position + 1));
            set(position + 1, item);
        }
    }

    private int getCardPosition(String idToSearch) {
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
