package com.sy.study.demo.network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author : sy
 * @date: 创建时间：2017年11月7日 上午9:35:05
 * @version: 1.0
 * @Description:
 */

public class DatagramSocketDemo {

	public static void main(String[] args) throws Exception {
		String ip = "10.1.0.28";
		int port = 12121;
		DatagramSocket datagramSocket = new DatagramSocket(port, InetAddress.getByName(ip));
		DatagramSocketReceiveDemo receiveDemo = new DatagramSocketReceiveDemo(datagramSocket);
		DatagramSocketSendDemo sendDemo = new DatagramSocketSendDemo(datagramSocket, port, ip);
		Thread sendThread = new Thread(sendDemo);
		Thread receiveThread = new Thread(receiveDemo);
		sendThread.start();
		receiveThread.start();
	}
}
