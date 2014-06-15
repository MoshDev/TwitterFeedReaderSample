package com.moshx.twitterfeeds.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.androidquery.callback.AjaxStatus;
import com.moshx.twitterfeeds.R;
import com.moshx.twitterfeeds.activities.MainActivity;
import com.moshx.twitterfeeds.adapters.FeedsAdapter;
import com.moshx.twitterfeeds.model.Tweet;
import com.moshx.twitterfeeds.network.requests.UserFeedsRequest;
import com.moshx.twitterfeeds.utils.Constants;
import com.moshx.twitterfeeds.utils.DialogsManager;
import com.quentindommerc.superlistview.OnMoreListener;
import com.quentindommerc.superlistview.SuperListview;

public class UserFeedsFragment extends SherlockFragment {

	private FeedsAdapter mAdapter;
	private SuperListview mListview;
	private MainActivity mActivity;
	private String mScreenName;

	public static UserFeedsFragment newInstance(String key) {
		Bundle bundle = new Bundle();
		bundle.putString("screenName", key);

		UserFeedsFragment fragment = new UserFeedsFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScreenName = getArguments().getString("screenName");
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parent = inflater.inflate(R.layout.fragment_feeds_list, container,
				false);
		initViews(parent, savedInstanceState);
		return parent;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getNewTweets();
	}

	@SuppressLint("ResourceAsColor")
	private void initViews(View parent, Bundle savedInstanceState) {

		mListview = (SuperListview) parent.findViewById(R.id.list);
		mListview.setAdapter(mAdapter = new FeedsAdapter(mActivity));

		mListview.setRefreshListener(onRefreshListener);
		mListview.setRefreshingColor(R.color.refresh_color_1,
				R.color.refresh_color_2, R.color.refresh_color_3,
				R.color.refresh_color_4);
		mListview.setupMoreListener(onMoreListener, 10);

	}

	private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {

		@Override
		public void onRefresh() {
			getNewTweets();

		}
	};

	private OnMoreListener onMoreListener = new OnMoreListener() {

		@Override
		public void onMoreAsked(int numberOfItems, int numberBeforeMore,
				int currentItemPos) {
			getOlderTweets();
		}
	};

	private void getNewTweets() {

		long since_id = 0;
		if (!mAdapter.isEmpty()) {
			since_id = mAdapter.getItem(0).getId();
		}

		new UserFeedsRequest(mActivity, mScreenName, since_id, 0) {

			public void onPreExecute() {
				if (mAdapter.getCount() <= 0) {
					mListview.showProgress();
				}
			};

			public void onCallBack(String url, Tweet[] result, AjaxStatus status) {

				if (result != null) {
					mAdapter.insertTweets(0, result);
					mListview.showList();
				} else {
					if (mAdapter.isEmpty()) {
						DialogsManager.showRetryRequestDialog(mActivity, this,
								RequestType.GET, new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (mAdapter.isEmpty()) {
											dialog.dismiss();
											mActivity.finish();
										}
									}
								});
					} else {
						mListview.getSwipeToRefresh().setRefreshing(false);
						DialogsManager.showToast(mActivity,
								"Failed to get new tweets.");
					}
				}

			};

		}.get();

	}

	private void getOlderTweets() {

		long max_id = 0;
		if (!mAdapter.isEmpty()) {
			max_id = mAdapter.getItem(mAdapter.getCount() - 1).getId();

		}

		new UserFeedsRequest(mActivity, mScreenName, 0, max_id) {

			public void onPreExecute() {
			};

			public void onCallBack(String url, Tweet[] result, AjaxStatus status) {

				mListview.showList();
				mListview.hideMoreProgress();

				if (result != null) {
					if (result.length < Constants.TWEETS_COUNT) {
						mListview.setLoadingMore(false);
					}
					mAdapter.addTweets(result);
				} else {
					Toast.makeText(mActivity, "Failed to get more feeds",
							Toast.LENGTH_SHORT).show();
				}

			};

		}.get();
	}

}
