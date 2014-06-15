package com.moshx.twitterfeeds.widgets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.ocpsoft.prettytime.PrettyTime;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;
import com.moshx.twitterfeeds.R;
import com.moshx.twitterfeeds.model.Tweet;
import com.moshx.twitterfeeds.utils.FontChanger;

public class TweetView extends RelativeLayout {

	private Tweet mTweet;
	private TextView mTweetView, mTweetTimeView, mUserNameView;
	private ImageView userImageView;
	private AQuery aQuery;
	private ImageOptions imageOptions;

	static SimpleDateFormat simpleDateFormat;
	static {
		simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy",
				Locale.US);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	static PrettyTime mPrettyTime = new PrettyTime(Locale.US);

	public TweetView(Context context) {
		this(context, null, 0);
	}

	public TweetView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TweetView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater.from(context).inflate(R.layout.view_tweet, this, true);

		FontChanger.setViewGroupFont(this,
				getResources().getString(R.string.font_default));

		mTweetView = (TextView) findViewById(R.id.tweetText);
		mTweetTimeView = (TextView) findViewById(R.id.tweetTime);
		mUserNameView = (TextView) findViewById(R.id.userName);
		userImageView = (ImageView) findViewById(R.id.userImage);

		aQuery = new AQuery(context);
		imageOptions = new ImageOptions();
//		imageOptions.animation = AQuery.FADE_IN_NETWORK;
		imageOptions.fallback = R.drawable.ic_launcher;
		imageOptions.preset = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
	}

	public void setTweet(Tweet t) {

		if (t.equals(mTweet)) {
			return;
		}

		mTweet = t;

		mTweetView.setText(t.getText());
		mUserNameView.setText(t.getUser().getName());

		try {
			mPrettyTime
					.setReference(new Date(System.currentTimeMillis() - 6000));
			Date d = simpleDateFormat.parse(t.getCreatedAt());
			mTweetTimeView.setText(mPrettyTime.format(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		aQuery.id(userImageView).image(t.getUser().getProfileImageUrl(),
				imageOptions);

	}

}
