package com.nucleartesuji.powerstruggleeventplanner;

import com.nucleartesuji.powerstruggleeventplanner.game.Card;

import android.content.res.Resources;
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
		
        displayMotivation(card);        
	}
	
	
	private void displayMotivation(Card card) {
		TextView motivation = (TextView) rootView.findViewById(R.id.cardMotivation);		

		if (card.getMotivationChange() == 0) {
			motivation.setVisibility(View.GONE);
		} else {
			motivation.setVisibility(View.VISIBLE);
			if (card.getMotivationChange() > 0) {
				motivation.setText("+" + card.getMotivationChange());
				motivation.setTextColor(res().getColor(R.color.positiveMotivation));
			} else {
				motivation.setText(Integer.valueOf(card.getMotivationChange()).toString());
				motivation.setTextColor(res().getColor(R.color.negativeMotivation));
			}
		}        
	}

	private Resources res() {
		return rootView.getContext().getResources();
	}

}
