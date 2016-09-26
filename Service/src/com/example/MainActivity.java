package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button startServiceButton = null;
	private Button stopServiceButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startServiceButton = (Button) findViewById(R.id.startService);
		startServiceButton.setOnClickListener(new StartServiceListener());
		stopServiceButton = (Button) findViewById(R.id.stopService);
		stopServiceButton.setOnClickListener(new StopServiceListener());
		System.out.println("Activity onCreate");
	}

	class StartServiceListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, FirstService.class);
			startService(intent);

		}

	}

	class StopServiceListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, FirstService.class);
			stopService(intent);

		}

	}

}
