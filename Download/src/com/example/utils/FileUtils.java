package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {
	private String SDPATH;

	public String getSDPATH() {
		return SDPATH;
	}

	public void setSDPATH(String sDPATH) {
		SDPATH = sDPATH;
	}

	public FileUtils() {
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}

	public File createFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;

	}


	public File createSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		return dir;
	}



	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	public File write2SDFromInput(String path, String fileName, InputStream inputStream) {
		File file = null;
		OutputStream outputStream = null;
		try {
			createSDDir(path);
			file = createFile(path + fileName);
			outputStream = new FileOutputStream(file);

			byte buffer[] = new byte[4 * 1024];
			while ((inputStream.read(buffer)) != -1) {
				outputStream.write(buffer);
			}

			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return file;

	}

}