package com.moshx.twitterfeeds.model;

import com.google.gson.annotations.SerializedName;

public class Tweet {

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private long id;

	@SerializedName("id_str")
	private String idStr;

	@SerializedName("text")
	private String text;

	@SerializedName("source")
	private String source;

	@SerializedName("user")
	private TwitterUser user;

	public String getCreatedAt() {
		return createdAt;
	}

	public long getId() {
		return id;
	}

	public String getIdStr() {
		return idStr;
	}

	public String getText() {
		return text;
	}

	public String getSource() {
		return source;
	}

	public TwitterUser getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweet other = (Tweet) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	// {
	// "created_at": "Fri Jun 13 21:05:41 +0000 2014",
	// "id": 477557440930930700,
	// "id_str": "477557440930930688",
	// "text": "مباراة الاحد عند\nHisham Ghatasheh",
	// "source":
	// "<a href=\"http://www.facebook.com/twitter\" rel=\"nofollow\">Facebook</a>",
	// "truncated": false,
	// "in_reply_to_status_id": null,
	// "in_reply_to_status_id_str": null,
	// "in_reply_to_user_id": null,
	// "in_reply_to_user_id_str": null,
	// "in_reply_to_screen_name": null,
	// "user": {
	// "id": 459088384,
	// "id_str": "459088384",
	// "name": "Mosh Ersan",
	// "screen_name": "MoshErsan",
	// "location": "Jordan - Amman",
	// "description": "Mobile Developer, interested in everything fabulous",
	// "url": "http://t.co/FizUZzWnah",
	// "entities": {
	// "url": {
	// "urls": [
	// {
	// "url": "http://t.co/FizUZzWnah",
	// "expanded_url": "http://bit.ly/Im2dR7",
	// "display_url": "bit.ly/Im2dR7",
	// "indices": [
	// 0,
	// 22
	// ]
	// }
	// ]
	// },
	// "description": {
	// "urls": []
	// }
	// },
	// "protected": false,
	// "followers_count": 76,
	// "friends_count": 198,
	// "listed_count": 0,
	// "created_at": "Mon Jan 09 08:23:44 +0000 2012",
	// "favourites_count": 40,
	// "utc_offset": null,
	// "time_zone": null,
	// "geo_enabled": false,
	// "verified": false,
	// "statuses_count": 2062,
	// "lang": "en",
	// "contributors_enabled": false,
	// "is_translator": false,
	// "is_translation_enabled": false,
	// "profile_background_color": "352726",
	// "profile_background_image_url":
	// "http://abs.twimg.com/images/themes/theme5/bg.gif",
	// "profile_background_image_url_https":
	// "https://abs.twimg.com/images/themes/theme5/bg.gif",
	// "profile_background_tile": false,
	// "profile_image_url":
	// "http://pbs.twimg.com/profile_images/1743207400/fb_normal.png",
	// "profile_image_url_https":
	// "https://pbs.twimg.com/profile_images/1743207400/fb_normal.png",
	// "profile_banner_url":
	// "https://pbs.twimg.com/profile_banners/459088384/1385246203",
	// "profile_link_color": "41B4EA",
	// "profile_sidebar_border_color": "000000",
	// "profile_sidebar_fill_color": "99CC33",
	// "profile_text_color": "3E4415",
	// "profile_use_background_image": true,
	// "default_profile": false,
	// "default_profile_image": false,
	// "following": null,
	// "follow_request_sent": null,
	// "notifications": null
	// }
}
