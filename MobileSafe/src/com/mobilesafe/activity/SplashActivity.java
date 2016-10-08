package com.mobilesafe.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mobilesafe.utils.StreamUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private int currentTime;
	private int mLocalVersionCode;
	private String mVersionDes;
	private String mDownloadUrl;
	protected static final String tag = "SplashActivity";
	protected static final int UPDATE_VERSION = 100;
	protected static final int ENTER_HOME = 101;
	protected static final int URL_ERROR = 102;
	protected static final int IO_ERROR = 103;
	protected static final int JSON_ERROR = 104;

	TextView versionName;
	private String packageName;
	String currentVersionName;
	// Uri url = new Uri("");
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_VERSION:
				System.out.println("UPDATE!!!");
				showUpdateDialog();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		initUI();
		initData();

	}

	protected void showUpdateDialog() {

		Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("版本更新");
		builder.setMessage(mVersionDes);
		builder.setPositiveButton("立即更新", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				enterHome();

			};
		});
		builder.setNegativeButton("稍后再说", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				enterHome();

			}
		});
		// 他么的！！！忘了调用show方法！！！
		builder.show();

	}

	protected void downloadApk() {
		if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
					+ "mobilesafe.apk";
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.download(mDownloadUrl, path, new RequestCallBack<File>() {

				@Override
				public void onSuccess(ResponseInfo<File> responseInfo) {
					Log.i(tag, "下载成功");
					File file = responseInfo.result;
					installApk(file);

				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Log.i(tag, "下载失败");

				}

				@Override
				public void onStart() {
					Log.i(tag, "刚刚开始下载");
				}

				@Override
				public void onLoading(long total, long current, boolean isUpLoading) {
					Log.i(tag, "下载中。。。。。");
					Log.i(tag, "total=" + total);
					Log.i(tag, "current=" + current);
					super.onLoading(total, current, isUpLoading);
				}

			});

		}
	}

	protected void installApk(File file) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.catagory.DEFAULT");
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivityForResult(intent, 0);
	}

	protected void enterHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();

	}

	// ..............................................................................................
	public void initData() {
		getVersionName();
		versionName.setText(currentVersionName);
		getVersionCode();
		mLocalVersionCode = getVersionCode();
		checkVersion();
	}

	public void initUI() {
		versionName = (TextView) findViewById(R.id.tv_version_name);

	}

	public void checkVersion() {

		new Thread() {
			public void run() {
				// Return a new Message instance from the global pool
				Message msg = Message.obtain();
				long startTime = System.currentTimeMillis();
				try {

					URL url = new URL("http://192.168.2.61:8080/Json_Version.json");
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					if (connection.getResponseCode() == 200) {
						InputStream inputStream = connection.getInputStream();
						String json = StreamUtil.stream2String(inputStream);
						Log.i(tag, json);
						// System.out.println("success!!!");
						JSONObject jsonObject = new JSONObject(json);
						String versionName = jsonObject.getString("versionName");
						String mVersionDes = jsonObject.getString("versionDes");
						String versionCode = jsonObject.getString("versionCode");
						String mDownloadUrl = jsonObject.getString("downloadUrl");

						if (mLocalVersionCode < Integer.parseInt(versionCode)) {
							msg.what = UPDATE_VERSION;

						} else {
							msg.what = ENTER_HOME;
						}
					}
				} catch (MalformedURLException e) {
				} catch (IOException e) {

				} catch (JSONException e) {

				} finally {
					long endTime = System.currentTimeMillis();
					if (endTime - startTime < 4000) {
						try {
							Thread.sleep(4000 - (endTime - startTime));

						} catch (Exception e2) {

						}

					}
					mHandler.sendMessage(msg);

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
