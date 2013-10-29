package com.nucleartesuji.powerstruggleeventplanner.game;

public class Card {

	String title;
	String text;
	int motivationChange;
	boolean standardEvent = false;
	
	public Card(String title, String text, int motivationChange) {
		this(title, text, motivationChange, false);
	}
	
	public Card(String title) {
		this(title, "");
	}
	
	public Card(String title, String text) {
		this(title, text, 0);
	}

	public Card(String title, String text, int motivationChange, boolean standardEvent) {
		this.title            = title;
		this.text             = text;
		this.motivationChange = motivationChange;
		this.standardEvent    = standardEvent;
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
			return new Card(title, text, motivationChange, standardEvent);
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
