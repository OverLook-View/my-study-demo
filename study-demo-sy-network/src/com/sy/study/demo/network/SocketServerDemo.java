package com.sy.study.demo.network;

import java.io.IOException;
import java.io.InputStream;
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
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = inputStream.read(buf);
		System.out.println(new String(buf, 0, len));
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("i see you".getBytes());
		socket.close();
		serverSocket.close();
	}
}
