package com.sy.study.demo.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author : sy
 * @date: 创建时间：2017年11月7日 下午1:55:22
 * @version: 1.0
 * @Description:
 */

public class ScoketClientDemo {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("10.1.0.28", 12121);
		//sendString(socket);
//		sendKeyIn(socket);
		sendFile(socket);
		socket.close();
	}

	private static void sendString(Socket socket) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("hello world!".getBytes());
		InputStream inputStream = socket.getInputStream();
		byte[] b = new byte[1024];
		int read = inputStream.read(b);
		System.out.println(new String(b, 0, read));
	}

	private static void sendKeyIn(Socket socket) throws IOException {
		OutputStream out = socket.getOutputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line);
			writer.newLine();
			writer.flush();
		}
	}
	
	private static void sendFile(Socket socket) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("aaa.txt"));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		String line=null;
		while ((line=reader.readLine())!=null) {
			writer.write(line);
			writer.newLine();
			writer.flush();
		}
		reader.close();
	}
}
