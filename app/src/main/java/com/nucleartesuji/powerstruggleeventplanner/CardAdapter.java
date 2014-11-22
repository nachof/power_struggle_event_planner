package com.nucleartesuji.powerstruggleeventplanner;

import com.nucleartesuji.powerstruggleeventplanner.game.Card;
import com.nucleartesuji.powerstruggleeventplanner.game.SortableHand;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CardAdapter extends ArrayAdapter<Card> {
	Context context;
	int layoutResourceId;
	SortableHand cards;

	public CardAdapter(Context context, int layoutResourceId, SortableHand cards) {
		super(context, layoutResourceId, cards);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.cards = cards;
	}
	
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        
        if(row == null)
        {
            row = generateView(parent);
        }
        
        Card card = cards.get(position);
        
        new CardDisplayer(row).display(card);
        
        row.findViewById(R.id.buttonUp).setTag(position);
        row.findViewById(R.id.buttonDown).setTag(position);
        
	    if (card.isStandardEvent()) {
	    	if (cards.validOrder()) {
	    		row.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColor));
	    	} else {
	    		row.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColorError));        		
	    	}
	    } else {
	    	row.setBackgroundColor(context.getResources().getColor(R.color.defaultEventBackgroundColor));
	    }
        
        return row;
    }

	private View generateView(ViewGroup parent) {
		View row = ((Activity)context).getLayoutInflater().inflate(layoutResourceId, parent, false);
		
		row.findViewById(R.id.buttonDown).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View button) {
				cards.moveCardDown((Integer) button.getTag());
				notifyDataSetChanged();						
			}            	
		});

		row.findViewById(R.id.buttonUp).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View button) {
				cards.moveCardUp((Integer) button.getTag());
				notifyDataSetChanged();
			}            	
		});
		
		return row;
	}

	static class CardHolder
    {
        TextView title;
        TextView text;
        TextView motivation;
        View buttonUp;
        View buttonDown;
    }
}