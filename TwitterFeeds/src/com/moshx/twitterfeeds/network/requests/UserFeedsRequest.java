package com.moshx.twitterfeeds.network.requests;

import android.content.Context;

import com.moshx.twitterfeeds.model.Tweet;
import com.moshx.twitterfeeds.network.AbstractRequest;
import com.moshx.twitterfeeds.utils.AppPref;

public class UserFeedsRequest extends AbstractRequest<Tweet[]> {

	public UserFeedsRequest(Context context, String screenName, long since_id,
			long max_id) {
		super(context, Tweet[].class);

		// if (search) {
		// setUrl("https://api.twitter.com/1.1/search/tweets.json");
		// addParameter("q", key);
		// } else {
		//
		// }

		setUrl("https://api.twitter.com/1.1/statuses/user_timeline.json");
		addParameter("screen_name", screenName);
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
