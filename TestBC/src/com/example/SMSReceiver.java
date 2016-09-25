package com.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("Receive message!!!");
		Bundle bundle = intent.getExtras();
		Object[] myOBJpdus = (Object[]) bundle.get("pdus");
		SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
		System.out.println(messages.length);
		for (int i = 0; i < myOBJpdus.length; i++) {
			messages[i] = SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
			System.out.println(messages[i].getDisplayMessageBody());
		}
		try {
			Thread.sleep(30 * 1000);
			System.out.println("-------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
