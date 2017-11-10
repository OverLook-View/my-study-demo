package com.sy.study.demo.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author : sy
 * @date: 创建时间：2017年11月6日 下午1:56:38
 * @version: 1.0
 * @Description:
 */

public class DatagramSocketReceiveDemo implements Runnable {

	private DatagramSocket ds;

	public DatagramSocketReceiveDemo(DatagramSocket ds) {
		super();
		this.ds = ds;
	}

	@Override
	public void run() {
		try {
			DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
			while (true) {
				ds.receive(datagramPacket);
				String ip = datagramPacket.getAddress().getHostAddress();
				System.out.println("[" + ip + "]" + new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket(12121);
		byte[] bs = new byte[1024];
		int length = bs.length;
		DatagramPacket datagramPacket = new DatagramPacket(bs, length);
		datagramSocket.receive(datagramPacket);
		InetAddress address = datagramPacket.getAddress();
		String res = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
		System.out.println(res);
		datagramSocket.close();
	}

}
