package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader {
	private URL url = null;

	public String download(String urlStr) {
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		BufferedReader bufferedReader = null;

		try {
			//
			url = new URL(urlStr);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}

	public int downloadFile(String url, String path, String fileName) {
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils();
			if (fileUtils.isFileExist(path + fileName)) {
				return 1;
			} else {
				inputStream = getInputStreamFromUrl(url);
				File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
				if (resultFile == null) {
					return -1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return -1;

		} finally {
			try {
				inputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}
		return 0;

	}

	public InputStream getInputStreamFromUrl(String urlStr) throws MalformedURLException, IOException {
		// ͨ��url��ַ��ȡurl����
		url = new URL(urlStr);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		InputStream inputStream = httpURLConnection.getInputStream();
		return inputStream;

	}

}
