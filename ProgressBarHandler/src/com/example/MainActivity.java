package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	ProgressBar bar = null;
	Button startButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar = (ProgressBar) findViewById(R.id.bar);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new ButtonListener());
	}

	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			bar.setVisibility(View.VISIBLE);
			updateBarHandler.post(updateThread);

		}

	}

	Handler updateBarHandler = new Handler() {

		@Override
		public void handleMessage(Message message) {
			bar.setProgress(message.arg1);
			Bundle bundle = message.getData();
			// post方法：往里面传入线程
			updateBarHandler.post(updateThread);
			System.out.println("test---->" + bundle.getString("test"));

		}

	};

	// int i = 0;
	Runnable updateThread = new Runnable() {
		int i = 0;

		public void run() {

			System.out.println("Begin Thread" + i);
			i = i + 10;
			Message message = updateBarHandler.obtainMessage();
			message.arg1 = i;
			Bundle bundle = new Bundle();
			bundle.putString("test", "test bundle");
			message.setData(bundle);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i > 100) {
				updateBarHandler.removeCallbacks(updateThread);
				System.out.println(">>>>>>>>");
			} else {
				updateBarHandler.handleMessage(message);
				System.out.println("<<<<<<<<");
			}

		}
	};

}
