package com.moshx.twitterfeeds;

import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.util.AQUtility;
import com.moshx.twitterfeeds.utils.AppPref;

import android.app.Application;

public class FeedsApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AQUtility.setDebug(true);
		AppPref.init(getApplicationContext());
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		BitmapAjaxCallback.clearCache();
	}

}
