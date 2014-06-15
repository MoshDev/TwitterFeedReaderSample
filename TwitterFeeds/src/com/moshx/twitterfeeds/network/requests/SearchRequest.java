package com.moshx.twitterfeeds.network.requests;

import android.content.Context;

import com.moshx.twitterfeeds.model.SearchResponse;
import com.moshx.twitterfeeds.network.AbstractRequest;
import com.moshx.twitterfeeds.utils.AppPref;

public class SearchRequest extends AbstractRequest<SearchResponse> {

	public SearchRequest(Context context, String key, long since_id, long max_id) {
		super(context, SearchResponse.class);

		setUrl("https://api.twitter.com/1.1/search/tweets.json");
		addParameter("q", key);

		addParameter("count", TWEETS_COUNT);

		if (since_id > 0) {
			addParameter("since_id", since_id);
		} else if (max_id > 0) {
			addParameter("max_id", max_id);
		}

		String token = AppPref.getInstance().getString(KEY_TWITTER_TOKEN);

		addHeader("Authorization", "Bearer " + token);
		addHeader("Content-Type", "application/json");

	}

}
