package com.gustavvy.ppefp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * HttpUtil.java
 *
 * @author Gustav V. Y
 */
public class HttpUtil {

	public static String get(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
