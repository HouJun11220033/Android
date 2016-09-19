package com.example.handlertest2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class HandlerTest extends Activity {
	private Handler handler = new Handler();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// sendMessage();
		// handler.post(r);
		setContentView(R.layout.main);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("a--->" + System.currentTimeMillis());
		// 把线程r放到线程t里面

		System.out.println("activity--->" + Thread.currentThread().getId());
		System.out.println("activityname--->" + Thread.currentThread().getName());
		Thread t = new Thread(r);
		t.start();

	}

	// 定义一个线程r
	Thread r = new Thread() {
		@Override
		public void start() {
			System.out.println("---------start---------");
			super.start();
		}

		@Override
		public void run() {
			System.out.println("r1--->" + System.currentTimeMillis());
			// TODO Auto-generated method stub
			System.out.println("handler--->" + Thread.currentThread().getId());
			System.out.println("handlername--->" + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
				System.out.println("r2---?" + System.currentTimeMillis());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	};

}