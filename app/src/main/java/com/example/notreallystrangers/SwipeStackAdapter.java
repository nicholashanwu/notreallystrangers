package com.example.notreallystrangers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notreallystrangers.model.Question;

import java.util.List;

public class SwipeStackAdapter extends BaseAdapter {

	private List<Question> mData;
	private Context context;

	public SwipeStackAdapter(List<Question> data, Context context) {
		this.mData = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Question getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {


		if(mData.get(position).getIsWildCard().equals("true")) {
			convertView = LayoutInflater.from(context).inflate(R.layout.wildcard, parent,false);
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.card, parent,false);
		}



		TextView cardBodyTextView = (TextView) convertView.findViewById(R.id.cardBodyTextView);
		cardBodyTextView.setText(mData.get(position).getQuestionBody().toUpperCase());

		cardBodyTextView.setText(mData.get(position).getQuestionId() + " - " + mData.get(position).getLevel() + " - " + mData.get(position).getQuestionBody().toUpperCase());

		TextView cardExpansionTextView = (TextView) convertView.findViewById(R.id.cardExpansionTextView);

		if(!mData.get(position).getQuestionType().equals("base pack")) {
			cardExpansionTextView.setText(mData.get(position).getQuestionType().toUpperCase() + " EXPANSION PACK");
		} else {
			cardExpansionTextView.setVisibility(View.INVISIBLE);
		}



		return convertView;
	}
}