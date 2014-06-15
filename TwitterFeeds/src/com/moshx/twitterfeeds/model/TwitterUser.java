package com.moshx.twitterfeeds.model;

import com.google.gson.annotations.SerializedName;

public class TwitterUser {

	@SerializedName("id")
	private long id;

	@SerializedName("id_str")
	private String idStr;

	@SerializedName("name")
	private String name;

	@SerializedName("screen_name")
	private String screenName;

	@SerializedName("location")
	private String location;

	@SerializedName("description")
	private String description;

	@SerializedName("url")
	private String url;

	@SerializedName("followers_count")
	private int followersCount;

	@SerializedName("friends_count")
	private int friendsCount;

	@SerializedName("statuses_count")
	private int statusesCount;

	@SerializedName("profile_background_color")
	private String profileBackgroundColor;

	@SerializedName("profile_background_image_url")
	private String profileBackgroundImageUrl;

	@SerializedName("profile_background_tile")
	private boolean profileBackgroundTile;

	@SerializedName("profile_image_url")
	private String profileImageUrl;

	@SerializedName("profile_sidebar_border_color")
	private String profileSidebarBorderColor;

	@SerializedName("profile_sidebar_fill_color")
	private String profileSidebarFillColor;

	@SerializedName("profile_text_color")
	private String profileTextColor;

	@SerializedName("profile_use_background_image")
	private boolean profileUseBackgroundImage;

	public long getId() {
		return id;
	}

	public String getIdStr() {
		return idStr;
	}

	public String getName() {
		return name;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount;
	}

	public int getStatusesCount() {
		return statusesCount;
	}

	public String getProfileBackgroundColor() {
		return profileBackgroundColor;
	}

	public String getProfileBackgroundImageUrl() {
		return profileBackgroundImageUrl;
	}

	public boolean isProfileBackgroundTile() {
		return profileBackgroundTile;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public String getProfileSidebarBorderColor() {
		return profileSidebarBorderColor;
	}

	public String getProfileSidebarFillColor() {
		return profileSidebarFillColor;
	}

	public String getProfileTextColor() {
		return profileTextColor;
	}

	public boolean isProfileUseBackgroundImage() {
		return profileUseBackgroundImage;
	}

	// "profile_sidebar_fill_color": "99CC33",
	// "profile_text_color": "3E4415",

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
