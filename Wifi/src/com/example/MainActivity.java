package com.example;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button startButton = null;
	private Button stopButton = null;
	private Button checkButton = null;
	private WifiManager wifiManager;

	// public MainActivity() {
	// WifiManager wifiManager = (WifiManager)
	// MainActivity.this.getSystemService(Context.WIFI_SERVICE);
	//
	// // super();
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startButton = (Button) findViewById(R.id.startWifi);
		stopButton = (Button) findViewById(R.id.stopWifi);
		checkButton = (Button) findViewById(R.id.checkWifi);
		startButton.setOnClickListener(new StartWifiListener());
		stopButton.setOnClickListener(new StopWifiListener());
		checkButton.setOnClickListener(new CheckWifiListener());
		wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);

	}

	class StartWifiListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 通过Activity对象拿到管理器
			// wifiManager = (WifiManager)
			// MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "!!!" + wifiManager.getWifiState(), Toast.LENGTH_LONG).show();

		}

	}

	class StopWifiListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// wifiManager = (WifiManager)
			// MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi----->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "!!!" + wifiManager.getWifiState(), Toast.LENGTH_LONG).show();

		}

	}

	class CheckWifiListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// wifiManager = (WifiManager)
			// MainActivity.this.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi state--->" + wifiManager.getWifiState());
			Toast.makeText(MainActivity.this, "!!!!" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();

		}

	}

}
