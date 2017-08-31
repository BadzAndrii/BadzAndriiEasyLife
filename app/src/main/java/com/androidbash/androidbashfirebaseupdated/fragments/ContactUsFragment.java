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
import android.widget.Toast;

import com.androidbash.androidbashfirebaseupdated.R;

public class ContactUsFragment extends Fragment {

	private Toolbar mToolbar;

	public ContactUsFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		Log.v("Activity:","Start ContactUsFragment");
		View rootView = inflater.inflate(R.layout.frag_about, container,
				false);

		getActivity().setTitle("Зв'яжіться з нами");
		
		rootView.findViewById(R.id.locations).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});

		rootView.findViewById(R.id.contact_num).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent callIntent = new Intent(Intent.ACTION_DIAL);
						callIntent.setData(Uri.parse("tel:" + "0965701328"));
						startActivity(callIntent);

					}
				});

		rootView.setFocusableInTouchMode(true);
		rootView.requestFocus();

		rootView.findViewById(R.id.site_dev).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent browserIntent = new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("https://www.facebook.com/vakoms/"));
						startActivity(browserIntent);

					}
				});

		rootView.findViewById(R.id.email).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						final Intent emailIntent = new Intent(
								android.content.Intent.ACTION_SEND);
						emailIntent.setType("text/plain");
						emailIntent
								.putExtra(
										android.content.Intent.EXTRA_EMAIL,
										new String[] { "andriy2021@gmail.com" });
						emailIntent.putExtra(
								android.content.Intent.EXTRA_SUBJECT,
								"Привіт");
						emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
								"Напишіть тут свій текст");

						emailIntent.setType("message/rfc822");

						try {
							startActivity(Intent.createChooser(emailIntent,
									"Виберіть програму для надсилання листа.."));
						} catch (android.content.ActivityNotFoundException ex) {
							Toast.makeText(getActivity(),
									"Немає жодного інстальованого клієнта для відправлення.",
									Toast.LENGTH_SHORT).show();
						}

					}
				});

		return rootView;
	}

	public static Fragment newInstance() {
		// TODO Auto-generated method stub
		return new ContactUsFragment();
	}
}
