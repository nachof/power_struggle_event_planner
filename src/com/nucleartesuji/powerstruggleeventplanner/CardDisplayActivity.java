package com.nucleartesuji.powerstruggleeventplanner;

import java.util.List;
import com.nucleartesuji.powerstruggleeventplanner.game.Card;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CardDisplayActivity extends Activity {
	private List<Card> cards;
	private int position;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_display);
		
		cards = (List<Card>) getIntent().getSerializableExtra(EventSortActivity.CARDS);
		position = 0;
		updateCardArea();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_display, menu);
		return true;
	}
	
	public void onNewDeckClick(View button){
		Builder builder = new AlertDialog.Builder(this);
		final Context ctx = this;
		OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(ctx, EventSortActivity.class));				
			}
		};
		builder.setTitle(R.string.new_deck_dialog_title)
			.setMessage(R.string.new_deck_dialog_message)
			.setPositiveButton(R.string.new_deck_dialog_yes, listener)
			.setNegativeButton(R.string.new_deck_dialog_no, null);
		
		builder.create().show();
		
		
	}
	
	public void onNextCardClick(View button){
		if (position < cards.size()-1) {
			position++;
			updateCardArea();
		} else {
			Toast.makeText(this, R.string.no_more_cards, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onPreviousCardClick(View button){
		if (position > 0) {
			position--;
			updateCardArea();
		} else {
			Toast.makeText(this, R.string.no_more_cards, Toast.LENGTH_SHORT).show();
		}
	}

	private void updateCardArea() {
		
		Card card = cards.get(position);
		
		View cardArea = findViewById(R.id.cardArea);
		
		new CardDisplayer(cardArea).display(card);
		
        TextView cardPosition = (TextView) cardArea.findViewById(R.id.cardPosition);
        cardPosition.setText(getResources().getString(R.string.card_position, position+1, cards.size()));
	}
}
