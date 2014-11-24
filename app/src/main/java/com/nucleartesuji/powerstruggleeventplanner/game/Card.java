package com.nucleartesuji.powerstruggleeventplanner.game;

import java.io.Serializable;

public class Card implements Serializable {

    private static final long serialVersionUID = -8450149522769530367L;

    public static final String DIRECTORS_MEETING = "DIRECTORS_MEETING";
    public static final String BONUS_PAYMENT = "BONUS_PAYMENT";

    private String title;
    private String text;
    private int motivationChange;
    private boolean standardEvent = false;

    private String cardId;

    public String longText;

    private Card(){
    }

    public String getTitle() {
        return title;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getText() {
        return text;
    }

    public int getMotivationChange() {
        return motivationChange;
    }

    public boolean isStandardEvent() {
        return standardEvent;
    }

    public String getCardId() {
        if (cardId != null) {
            return cardId;
        } else {
            return Integer.toString(hashCode());
        }
    }

    public static class Builder {
        private String text;
        private String title;
        private int motivationChange;
        private boolean standardEvent;
        private String cardId;
        private String longText;

        public Card build() {
            Card c = new Card();
            c.title = title;
            c.text = text;
            c.motivationChange = motivationChange;
            c.standardEvent = standardEvent;
            c.cardId = cardId;
            c.longText = longText;

            return c;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setMotivationChange(String motivationChange) {
            this.motivationChange = Integer.parseInt(motivationChange);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setStandardEvent(boolean e) {
            this.standardEvent = e;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public void setLongText(String longText) {
            this.longText = longText;
        }

    }

    public boolean hasCardId(String cardId) {
        return this.getCardId() != null && this.getCardId().equals(cardId);
    }

    public String getLongText() {
        return longText;
    }
}
