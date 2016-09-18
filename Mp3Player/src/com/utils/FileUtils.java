package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileLockInterruptionException;

import android.os.Environment;

public class FileUtils {
	private String SDCardRoot;

	public FileUtils() {
		SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

	}

	// 创建文件
	public File createFileInSDCard(String fileName, String dir) throws IOException {
		File file = new File(SDCardRoot + dir + File.separator + fileName);
		file.createNewFile();
		return file;

	}

	// 创建目录
	public File createSDDir(String dir) {
		File dirFile = new File(SDCardRoot + dir + File.separator);
		System.out.println(dirFile.mkdirs());
		return dirFile;

	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName, String path) {
		File file = new File(SDCardRoot + path + File.separator + fileName);
		return file.exists();
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream outputStream = null;
		try {
			createSDDir(path);
			// 创建空文件

			file = createFileInSDCard(fileName, path);
			// 这个outputStream是属于文件拿到输入流
			outputStream = new FileOutputStream(file);

			byte buffer[] = new byte[4 * 1024];
			int temp;
			// 从输入流里面读取
			// 输出里面写
			while ((temp = input.read(buffer)) != -1) {
				// 输出流没有read方法
				outputStream.write(buffer, 0, temp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return file;

	}

}
