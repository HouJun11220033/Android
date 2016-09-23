package com.example;

import android.app.Activity;
import android.os.Bundle;

public class TargetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("Success !!!");
	}

}
