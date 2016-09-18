package com.download;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {
	public String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		// 每一行的数据
		String line = null;
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				bufferedReader.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return sb.toString();

	}

}
