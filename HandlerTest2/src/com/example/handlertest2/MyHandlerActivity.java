package com.example.handlertest2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

public class MyHandlerActivity extends Activity {
	Button button = null;
	MyHandler myHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.testHandler);

	}

	class MyHandler extends Handler {
		public MyHandler() {
		}

		public MyHandler(Looper L) {
			super(L);
		}

		@Override
		public void handleMessage(Message msg) {
			Log.d("MyHandler", "handleMessage...");

			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			String color = bundle.getString("color");
			MyHandlerActivity.this.button.append(color);
		}

	}

	class Mythread implements Runnable {
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println("!!!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.d("thread...", "MyThread....");
			Message message = new Message();
			Bundle bundle = new Bundle();
			bundle.putString("color", "my");
			message.setData(bundle);
			MyHandlerActivity.this.myHandler.sendMessage(message);

		}

	}

}
