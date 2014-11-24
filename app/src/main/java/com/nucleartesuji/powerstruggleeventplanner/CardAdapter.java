package com.nucleartesuji.powerstruggleeventplanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nucleartesuji.powerstruggleeventplanner.game.Card;
import com.nucleartesuji.powerstruggleeventplanner.game.SortableHand;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private final Context context;
    private final SortableHand cards;

    public CardAdapter(Context context, SortableHand cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Card card = cards.get(position);

        holder.title.setText(card.getTitle());
        holder.text.setText(card.getText());

        if (card.getMotivationChange() == 0) {
            holder.motivation.setVisibility(View.GONE);
        } else {
            holder.motivation.setVisibility(View.VISIBLE);
            if (card.getMotivationChange() > 0) {
                holder.motivation.setText("+" + card.getMotivationChange());
                holder.motivation.setTextColor(context.getResources().getColor(R.color.positiveMotivation));
            } else {
                holder.motivation.setText(Integer.valueOf(card.getMotivationChange()).toString());
                holder.motivation.setTextColor(context.getResources().getColor(R.color.negativeMotivation));
            }
        }

        holder.buttonUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = cards.getCardPosition(card);
                if (position > 0) {
                    cards.moveCardUp(position);
                    notifyItemMoved(position, position - 1);
                }
            }
        });

        holder.buttonDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = cards.getCardPosition(card);
                if (position < cards.size() - 1) {
                    cards.moveCardDown(position);
                    notifyItemMoved(position, position + 1);
                }
            }
        });

        if (card.isStandardEvent()) {
            if (cards.validOrder()) {
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColor));
            } else {
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.standardEventBackgroundColorError));
            }
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.defaultEventBackgroundColor));
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView text;
        public TextView motivation;
        public View buttonUp;
        public View buttonDown;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.cardTitle);
            motivation = (TextView) itemView.findViewById(R.id.cardMotivation);
            text = (TextView) itemView.findViewById(R.id.cardText);
            buttonDown = itemView.findViewById(R.id.buttonDown);
            buttonUp = itemView.findViewById(R.id.buttonUp);
        }
    }
}