package com.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;

public class MainActivity extends Activity {
	private RatingBar ratingBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ratingBar = (RatingBar) findViewById(R.id.ratingbarId);
		ratingBar.setOnRatingBarChangeListener(new RatingBarListener());
	}

	private class RatingBarListener implements
			RatingBar.OnRatingBarChangeListener {

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
			System.out.println("rating--->" + rating);
		}

	}
	// class test extends RatingBar{
	//
	// public test(Context context) {
	// super(context);
	// // TODO Auto-generated constructor stub
	// }
	//
	// }

}
