package com.example;

import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonUtils {
	public void parseJson(String jsonData) {
		try {
			JsonReader jsonReader = new JsonReader(new StringReader(jsonData));
			// 开始解析数组
			jsonReader.beginArray();
			while (jsonReader.hasNext()) {
				// 开始解析对象
				jsonReader.beginObject();
				while (jsonReader.hasNext()) {
					String tagName = jsonReader.nextName();
					if (tagName.equals("name")) {
						System.out.println("name--->" + jsonReader.nextString());
					} else if (tagName.equals("age")) {
						System.out.println("age--->" + jsonReader.nextInt());

					}
				}
				jsonReader.endObject();

			}
			jsonReader.endArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void parseUserFromJson(String jsonData) {
		Gson gson = new Gson();
		User user = gson.fromJson(jsonData, User.class);

		System.out.println("name--->" + user.getName());

		System.out.println("age--->" + user.getAge());
	}

}
