package com.example;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {
	TextView versionName;
	private String packageName;
	String currentVersionName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initUI();
		initData();

	}

	public void initData() {
		getVersionName();
		versionName.setText(currentVersionName);

	}

	public void initUI() {
		versionName = (TextView) findViewById(R.id.tv_version_name);

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
