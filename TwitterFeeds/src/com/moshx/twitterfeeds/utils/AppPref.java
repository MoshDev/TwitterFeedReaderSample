package com.moshx.twitterfeeds.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPref {

	private SharedPreferences preferences;

	private static AppPref pref;

	public static void init(Context context) {
		pref = new AppPref(context);
	}

	private AppPref(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public static AppPref getInstance() {
		return pref;
	}

	public String getString(String key, String defaultCValue) {
		return preferences.getString(key, defaultCValue);
	}

	public String getString(String key) {
		return getString(key, null);
	}

	public void putString(String key, String value) {
		preferences.edit().putString(key, value).commit();
	}

}
