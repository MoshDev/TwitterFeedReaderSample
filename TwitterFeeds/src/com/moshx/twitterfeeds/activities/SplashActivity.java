package com.moshx.twitterfeeds.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import com.androidquery.callback.AjaxStatus;
import com.moshx.twitterfeeds.R;
import com.moshx.twitterfeeds.model.TokenResponse;
import com.moshx.twitterfeeds.network.requests.TokenRequest;
import com.moshx.twitterfeeds.utils.AppPref;
import com.moshx.twitterfeeds.utils.Constants;
import com.moshx.twitterfeeds.utils.DialogsManager;

public class SplashActivity extends Activity implements Constants {

	private ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mProgressBar = (ProgressBar) findViewById(R.id.progress);

		String token = AppPref.getInstance().getString(KEY_TWITTER_TOKEN);
		if (TextUtils.isEmpty(token)) {
			getTwitterToken();
		} else {
			goToNextScreen();
		}
	}

	private void goToNextScreen() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();
				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));

			}
		}, 1000);

	}

	private void getTwitterToken() {
		new TokenRequest(this) {

			public void onPreExecute() {
				mProgressBar.setVisibility(View.VISIBLE);
			};

			public void onCallBack(String url, TokenResponse result,
					AjaxStatus status) {

				if (result != null) {
					if (result.isSuccess()) {

						AppPref.getInstance().putString(KEY_TWITTER_TOKEN,
								result.getAccessToken());
						goToNextScreen();

					} else {
						DialogsManager.showRetryRequestDialog(
								SplashActivity.this, this, RequestType.POST,
								new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
										finish();
									}
								});
					}

				} else {
					DialogsManager.showRetryRequestDialog(SplashActivity.this,
							this, RequestType.POST, new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									finish();
								}
							});
				}

			};

		}.post();
	}

}
