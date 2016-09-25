package com.example;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button registerButton = null;
	private Button unregisterButton = null;
	private SMSReceiver smsReceiver = null;
	private Button sendButton;
	private static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.testreceiver);
		registerButton = (Button) findViewById(R.id.register);
		registerButton.setOnClickListener(new RegisterReceiverListener());
		unregisterButton = (Button) findViewById(R.id.unregister);
		unregisterButton.setOnClickListener(new UnRegisterReceiverListener());
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

	class RegisterReceiverListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			smsReceiver = new SMSReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(SMS_ACTION);
			MainActivity.this.registerReceiver(smsReceiver, filter);

		}

	}

	class UnRegisterReceiverListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MainActivity.this.unregisterReceiver(smsReceiver);

		}

	}

}
