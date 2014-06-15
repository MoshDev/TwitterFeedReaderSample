package com.moshx.twitterfeeds.network.requests;

import java.net.URLEncoder;

import android.content.Context;
import android.util.Base64;

import com.moshx.twitterfeeds.model.TokenResponse;
import com.moshx.twitterfeeds.network.AbstractRequest;

public class TokenRequest extends AbstractRequest<TokenResponse> {

	public TokenRequest(Context context) {
		super(context, TokenResponse.class);

		setUrl("https://api.twitter.com/oauth2/token");

		try {

			String urlApiKey = URLEncoder.encode(TWITTER_API_KEY, "UTF-8");
			String urlApiSecret = URLEncoder
					.encode(TWITTER_API_SECRET, "UTF-8");
			String combined = urlApiKey + ":" + urlApiSecret;
			String base64Encoded = Base64.encodeToString(combined.getBytes(),
					Base64.NO_WRAP);

			addHeader("Authorization", "Basic " + base64Encoded);
			addHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			addParameter("grant_type", "client_credentials");

		} catch (Exception e) {
			// this should not happen
			e.printStackTrace();
		}
	}

}
