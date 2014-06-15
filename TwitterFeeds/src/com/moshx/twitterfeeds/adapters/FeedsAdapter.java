package com.moshx.twitterfeeds.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.moshx.twitterfeeds.model.Tweet;
import com.moshx.twitterfeeds.widgets.TweetView;

public class FeedsAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Tweet> tweets = new ArrayList<>();

	public FeedsAdapter(Context c) {
		mContext = c;
	}

	@Override
	public int getCount() {
		return tweets.size();
	}

	@Override
	public Tweet getItem(int position) {
		return tweets.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = new TweetView(mContext);
		}

		Tweet tweet = getItem(position);

		TweetView tweetView = (TweetView) convertView;
		tweetView.setTweet(tweet);

		return convertView;
	}

	public void addTweet(Tweet t) {
		tweets.add(t);
		notifyDataSetChanged();
	}

	public void addTweets(Tweet[] ts) {
		tweets.addAll(Arrays.asList(ts));
		notifyDataSetChanged();
	}

	public void addTweets(List<Tweet> ts) {
		tweets.addAll(ts);
		notifyDataSetChanged();
	}

	public void insertTweet(int index, Tweet t) {
		tweets.add(index, t);
		notifyDataSetChanged();
	}

	public void insertTweets(int index, Tweet[] ts) {
		tweets.addAll(index, Arrays.asList(ts));
		notifyDataSetChanged();
	}

	public void insertTweets(int index, List<Tweet> ts) {
		tweets.addAll(index, ts);
		notifyDataSetChanged();
	}
	
	public void clear(){
		tweets.clear();
		notifyDataSetChanged();
	}

}
