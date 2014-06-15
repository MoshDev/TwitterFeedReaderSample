package com.moshx.twitterfeeds.model;

import com.google.gson.annotations.SerializedName;

public class SearchResponse extends TwitterResponse {

	@SerializedName("statuses")
	private Tweet[] statuses;

	public Tweet[] getStatuses() {
		return statuses;
	}

}
