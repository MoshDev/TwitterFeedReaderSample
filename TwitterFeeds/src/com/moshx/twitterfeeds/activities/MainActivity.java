package com.moshx.twitterfeeds.activities;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.moshx.twitterfeeds.R;
import com.moshx.twitterfeeds.fragments.SearchFragment;
import com.moshx.twitterfeeds.fragments.UserFeedsFragment;
import com.moshx.twitterfeeds.utils.FontChanger;

public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FontChanger.setActionBarTitleFont(this,
				getString(R.string.font_default));

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.container, UserFeedsFragment.newInstance("MoshErsan"))
				.commit();
	}

	public void showSearchFragment(String key) {

		SearchFragment fragment = (SearchFragment) getSupportFragmentManager()
				.findFragmentByTag("search_fragment");
		if (fragment != null && fragment.isVisible()) {
			fragment.doSearch(key);

		} else {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, SearchFragment.newInstance(key))
					.addToBackStack("search_fragment").commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		final MenuItem searchMenuItem = menu.findItem(R.id.search);
		SearchView searchView = (SearchView) searchMenuItem.getActionView();
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				searchMenuItem.collapseActionView();
				showSearchFragment(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
		return true;
	}

}
