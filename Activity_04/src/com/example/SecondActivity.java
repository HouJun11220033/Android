package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {

	private Button secondButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		secondButton = (Button) findViewById(R.id.secondButton);
		// Gallery g = (Gallery) findViewById(R.id.secondButton);
		secondButton.setOnClickListener(new ButtonOnClickListener());

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onDestory");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		System.out.println("SecondActivity--->onStop");
		super.onStop();
	}

	class ButtonOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(SecondActivity.this, FirstActivity.class);
			SecondActivity.this.startActivity(intent);
		}

	}
}
