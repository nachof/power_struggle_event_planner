package com.nucleartesuji.powerstruggleeventplanner.game;

public class Card {

	String title;
	String text;
	int motivationChange;
	
	public Card(String title, String text, int motivationChange) {
		this.title = title;
		this.text = text;
		this.motivationChange = motivationChange;
	}
	
	public Card(String title) {
		this(title, "");
	}
	
	public Card(String title, String text) {
		this(title, text, 0);
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
	
	public static class Builder {
		private String text;
		private String title;
		private int motivationChange;
		
		public Card build() {
			return new Card(title, text, motivationChange);
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

	}

	public int getMotivationChange() {
		return motivationChange;
	}
}
