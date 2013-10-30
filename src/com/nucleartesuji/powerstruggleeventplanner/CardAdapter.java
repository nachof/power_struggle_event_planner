package com.nucleartesuji.powerstruggleeventplanner;

import com.nucleartesuji.powerstruggleeventplanner.game.Card;
import com.nucleartesuji.powerstruggleeventplanner.game.SortableHand;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
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
        CardHolder holder = null;
        
        if(row == null)
        {
            holder = new CardHolder();
            row = generateView(parent, holder);
        } else {
            holder = (CardHolder)row.getTag();
        }
        
        Card card = cards.get(position);
        holder.title.setText(card.getTitle());
        holder.text.setText(card.getText());
        displayMotivation(holder.motivation, card);
        if (card.isStandardEvent()) {
        	if (cards.validOrder()) {
        		row.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColor));
        	} else {
        		row.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColorError));        		
        	}
        } else {
        	row.setBackgroundColor(context.getResources().getColor(R.color.defaultEventBackgroundColor));
        }
        holder.buttonUp.setTag(position);
        holder.buttonDown.setTag(position);
        
        return row;
    }

	private View generateView(ViewGroup parent, CardHolder holder) {
		View row;
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);
		
		holder.title = (TextView)row.findViewById(R.id.cardTitle);
		holder.text = (TextView)row.findViewById(R.id.cardText);
		holder.motivation = (TextView)row.findViewById(R.id.cardMotivation);
		holder.buttonUp = (View)row.findViewById(R.id.buttonUp);
		holder.buttonDown = (View)row.findViewById(R.id.buttonDown);
		
		holder.buttonDown.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View button) {
				cards.moveCardDown((Integer) button.getTag());
				notifyDataSetChanged();						
			}            	
		});

		holder.buttonUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View button) {
				cards.moveCardUp((Integer) button.getTag());
				notifyDataSetChanged();
			}            	
		});
		
		row.setTag(holder);
		return row;
	}

	private void displayMotivation(TextView view, Card card) {
		if (card.getMotivationChange() == 0) {
			view.setVisibility(View.GONE);
		} else {
			view.setVisibility(View.VISIBLE);
			if (card.getMotivationChange() > 0) {
				view.setText("+" + card.getMotivationChange());
				view.setTextColor(context.getResources().getColor(R.color.positiveMotivation));
			} else {
				view.setText(Integer.valueOf(card.getMotivationChange()).toString());
				view.setTextColor(context.getResources().getColor(R.color.negativeMotivation));
			}
		}        
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