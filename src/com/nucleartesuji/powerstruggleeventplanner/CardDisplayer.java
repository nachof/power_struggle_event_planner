package com.nucleartesuji.powerstruggleeventplanner;

import com.nucleartesuji.powerstruggleeventplanner.game.Card;

import android.view.View;
import android.widget.TextView;

public class CardDisplayer {
	private View rootView; 

	public CardDisplayer(View cardArea) {
		rootView = cardArea;
	}

	public void display(Card card) {
		((TextView) rootView.findViewById(R.id.cardTitle)).setText(card.getTitle());
		((TextView) rootView.findViewById(R.id.cardText)).setText(card.getText());
		
		TextView motivation = (TextView) rootView.findViewById(R.id.cardMotivation);		
		
        displayMotivation(motivation, card);

	}
	
	private void displayMotivation(TextView view, Card card) {
		if (card.getMotivationChange() == 0) {
			view.setVisibility(View.GONE);
		} else {
			view.setVisibility(View.VISIBLE);
			if (card.getMotivationChange() > 0) {
				view.setText("+" + card.getMotivationChange());
				view.setTextColor(rootView.getContext().getResources().getColor(R.color.positiveMotivation));
			} else {
				view.setText(Integer.valueOf(card.getMotivationChange()).toString());
				view.setTextColor(rootView.getContext().getResources().getColor(R.color.negativeMotivation));
			}
		}        
	}

}
