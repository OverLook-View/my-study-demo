package com.sy.study.demo.network;

import java.io.InputStream;
import java.io.OutputStream;
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
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("hello world!".getBytes());
		InputStream inputStream = socket.getInputStream();
		byte[] b=new byte[1024];
		int read = inputStream.read(b);
		System.out.println(new String(b, 0, read));
		socket.close();
	}
}
