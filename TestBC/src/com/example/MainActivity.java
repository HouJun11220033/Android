package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testreceiver);
		sendButton = (Button) findViewById(R.id.sendButton);
		sendButton.setOnClickListener(new BoardcastListener());
		System.out.println("1");
	}

	class BoardcastListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_EDIT);
			MainActivity.this.sendBroadcast(intent);
			System.out.println("2");

		}

	}

}
