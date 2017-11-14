package com.sy.study.demo.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : sy
 * @date: 创建时间：2017年11月7日 下午2:05:17
 * @version: 1.0
 * @Description:
 */

public class SocketServerDemo {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(12121);
		Socket socket = serverSocket.accept();
		// receiveString(socket);
//		receiveKeyInToFile(socket);
		receiveFile(socket);
		socket.close();
		serverSocket.close();
	}

	private static void receiveString(Socket socket) throws IOException {
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = inputStream.read(buf);
		System.out.println(new String(buf, 0, len));
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(("i see you , you send : " + new String(buf, 0, len)).getBytes());
	}

	private static void receiveKeyInToFile(Socket socket) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter writer = new BufferedWriter(new FileWriter("aaa.txt"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			writer.write(line);
			writer.newLine();
			writer.flush();
		}
		reader.close();
		writer.close();
	}

	private static void receiveFile(Socket socket) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}
}
