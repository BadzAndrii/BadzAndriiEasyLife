package com.androidbash.androidbashfirebaseupdated.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidbash.androidbashfirebaseupdated.R;

// TODO: Auto-generated Javadoc

public class SettingsFragment extends Fragment {

	private TextView submitLog;
	private Toolbar mToolbar;

	/**
	 * Instantiates a new settings fragment.
	 */
	public SettingsFragment() {
		// Empty constructor required for fragment subclasses
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		Log.v("Activity:","Start SettingsFragment");
		View rootView = inflater.inflate(R.layout.frag_settings, container,
				false);

		getActivity().setTitle("Про програму");

		rootView.setFocusableInTouchMode(true);
		rootView.requestFocus();

		rootView.findViewById(R.id.email_dev).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent browserIntent = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://www.facebook.com/vakoms/"));
						startActivity(browserIntent);

					}
				});
		return rootView;
	}

	public static Fragment newInstance() {
		// TODO Auto-generated method stub
		return new SettingsFragment();
	}
}
