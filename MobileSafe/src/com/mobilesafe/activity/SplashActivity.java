package com.mobilesafe.activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.R;
import com.mobilesafe.utils.StreamUtil;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Administrator
 *
 */
public class SplashActivity extends Activity {
	protected static final String tag = "SplashActivity";

	TextView versionName;
	private String packageName;
	String currentVersionName;
	// Uri url = new Uri("");

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		initUI();
		initData();
		System.out.println("!!!");

	}

	// ..............................................................................................
	public void initData() {
		getVersionName();
		versionName.setText(currentVersionName);
		getVersionCode();

		checkVersion();

	}

	public void initUI() {
		versionName = (TextView) findViewById(R.id.tv_version_name);

	}

	public void checkVersion() {

		new Thread() {
			public void run() {
				try {
					URL url = new URL("http://192.168.2.61:8080/Json_Version.json");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					if (connection.getResponseCode() == 200) {
						InputStream inputStream = connection.getInputStream();
						String json = StreamUtil.stream2String(inputStream);
						Log.i(tag, json);
						System.out.println("success!!!");
					}
				} catch (Exception e) {
				}
			};
		}.start();
	}

	// public class text implements Runnable {
	// public void run() {
	// System.out.println("!!!");
	// }
	//
	// }

	/**
	 * 返回版本号
	 * 
	 * @return 非0标识获取成功
	 */
	public int getVersionCode() {
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public String getVersionName() {
		PackageManager packageManager = getPackageManager();
		try {
			// 参数必须传入包名：getPackageName()
			PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
			currentVersionName = packageInfo.versionName;
			return currentVersionName;

		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}

		return null;

	}

}
