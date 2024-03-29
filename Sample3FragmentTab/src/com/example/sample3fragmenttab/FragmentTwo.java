package com.example.sample3fragmenttab;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTwo extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tab_two, container, false);
		return v;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.f2_menu, menu);
		MenuItem item = menu.findItem(R.id.f2m1);
		ShareActionProvider provider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);
		provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);

		Intent shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
		provider.setShareIntent(shareIntent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((ActionBarActivity)getActivity()).getSupportActionBar().setTitle("F2");
	}
	
}
