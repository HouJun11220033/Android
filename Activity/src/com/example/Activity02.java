package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity02 extends Activity {
	private Object test;
	private Button myButton = null;
	// test git

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myButton = (Button) findViewById(R.id.myButton);
		myButton.setText("Click Me !!!");
		myButton.setOnClickListener(new MyButtonListener());
	}

	class MyButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("testIntent", "123");
			intent.setClass(Activity02.this, OtherActivity.class);
			Activity02.this.startActivity(intent);

		}

	}

}