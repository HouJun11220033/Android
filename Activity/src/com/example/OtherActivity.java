package com.example;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class OtherActivity extends Activity {
	private TextView myTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		// 坑啊！要调用这个方法！！！
		// Return the intent that started this activity.

		Intent intent = getIntent();
		String value = intent.getStringExtra("testIntent");
		myTextView = (TextView) findViewById(R.id.myTextView);
		// myTextView.setText("Haaaaaaaa");
		myTextView.setText(value);

	}

}
