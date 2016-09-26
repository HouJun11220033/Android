package com.example;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirstService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Service onBind");
		return null;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		System.out.println("Service onCreate");
	}

	// Do not call this method directly!!!
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("flag--->" + flags);
		System.out.println("startId---->" + startId);
		System.out.println("Service onStartCommand");

		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		System.out.println("Service onDestory");
		super.onDestroy();
	}

}
