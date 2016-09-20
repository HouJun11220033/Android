package com.example.download;

import com.example.utils.HttpDownloader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Download extends Activity {
	private Button downloadTxtButton;
	private Button downloadMp3Button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		downloadTxtButton = (Button) findViewById(R.id.downloadTxt);
		downloadMp3Button = (Button) findViewById(R.id.downloadMp3);
		downloadTxtButton.setOnClickListener(new DownloadTxtListener());
		downloadMp3Button.setOnClickListener(new DownloadMp3Listener());

	}

	class DownloadTxtListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			HttpDownloader httpDownloader = new HttpDownloader();
			String lrcString = httpDownloader.download("");
			System.out.println(lrcString);

		}
	}

	class DownloadMp3Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			HttpDownloader httpDownloader = new HttpDownloader();
			int result = httpDownloader.downloadFile(
					"http://ftp-idc.pconline.com.cn/3fcf72e07f22f212af324b7f78187c52/pub/download/201010/FlashFXP54_3939_Setup.exe",
					"downloadTest/", "FlashFXP54_3939_Setup.exe");
			System.out.println(result);

		}

	}

}
