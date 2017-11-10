package com.sy.study.demo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author : sy
 * @date: 创建时间：2017年11月6日 下午1:21:46
 * @version: 1.0
 * @Description:
 */

public class DatagramSocketSendDemo implements Runnable {
	
	private DatagramSocket ds;
	private int port;
	private String ip; 

	public DatagramSocketSendDemo(DatagramSocket ds, int port, String ip) {
		super();
		this.ds = ds;
		this.port = port;
		this.ip = ip;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String line=null;
			while ((line=bufferedReader.readLine())!=null) {
				byte[] bs = line.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(bs, bs.length,InetAddress.getByName(ip),port);
				ds.send(datagramPacket);
			}
			ds.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket();
		byte[] bs = "这是socketdemo1".getBytes();
		int length = bs.length;
		InetAddress inetAddress = InetAddress.getByName("10.1.0.28");
		int port = 12121;
		DatagramPacket packet = new DatagramPacket(bs, length, inetAddress, port);
		datagramSocket.send(packet);
		datagramSocket.close();
	}
}
