package com.sy.study.demo.url;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : sy
 * @date: 创建时间：2017年11月16日 下午2:33:14
 * @version: 1.0
 * @Description:
 */

public class UrlConnectionDemo {

	public static void main(String[] args) throws Exception {
		String urlString = "http://www.baidu.com";
		urlConnect(urlString);
//		downloadImage(urlString);
	}

	public static void urlConnect(String urlString) throws Exception {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		byte[] buf = new byte[1024 * 100];
		int i = inputStream.read(buf);
		System.out.println(new String(buf, 0, i));
	}

	public static void downloadImage(String urlString) throws Exception {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line=null;
		while((line=reader.readLine())!=null){
			String regex="[\\w/]+\\.jpg";
			Pattern p=Pattern.compile(regex);
			Matcher matcher = p.matcher(line);
			while(matcher.find()){
				String group = matcher.group();
				System.out.println(group);
			}
		}
	}
}
