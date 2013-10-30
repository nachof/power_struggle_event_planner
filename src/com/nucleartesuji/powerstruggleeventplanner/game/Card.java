package com.nucleartesuji.powerstruggleeventplanner.game;

public class Card {

	String title;
	String text;
	int motivationChange;
	boolean standardEvent = false;
	
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
	
	public static class Builder {
		private String text;
		private String title;
		private int motivationChange;
		private boolean standardEvent;
		
		public Card build() {
			Card c = new Card();
			c.title = title;
			c.text = text;
			c.motivationChange = motivationChange;
			c.standardEvent = standardEvent;
					
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

	}
}
