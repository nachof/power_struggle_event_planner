package com.nucleartesuji.powerstruggleeventplanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.nucleartesuji.powerstruggleeventplanner.game.Deck;
import com.nucleartesuji.powerstruggleeventplanner.game.SortableHand;

public class EventSortActivity extends Activity {

	private SortableHand cards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_sort);
		
		loadCardsData();
		
		cards = Deck.getInstance().getDrawnCards();
		
		CardAdapter adapter = new CardAdapter(this, R.layout.card_item, cards);
		ListView cardList = (ListView) findViewById(R.id.cardList);
		cardList.setAdapter(adapter);
	}

	private void loadCardsData() {
		Deck.getInstance().loadCardsData(getResources().openRawResource(R.raw.cards));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_sort, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.menuItemConfirmOrder:
	            doConfirmOrder();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void doConfirmOrder() {
		if (cards.validOrder()) {
			// TODO: Go to next activity, this one's done
		} else {
			Toast.makeText(this, R.string.card_order_invalid, Toast.LENGTH_SHORT).show();
		}
	}
}
