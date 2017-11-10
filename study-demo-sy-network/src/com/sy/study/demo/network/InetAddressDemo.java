package com.sy.study.demo.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/** 
 * @author : sy
 * @date: 创建时间：2017年11月6日 上午10:11:20 
 * @version: 1.0   
 * @Description:   
 */

public class InetAddressDemo {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
		for (InetAddress inetAddress : addresses) {
			String hostAddress = inetAddress.getHostAddress();
			System.out.println(hostAddress);
			String hostName = inetAddress.getHostName();
			System.out.println(hostName);
		}
		InetAddress address = InetAddress.getByName("10.1.0.29");
		System.out.println(address.getHostAddress());
		System.out.println(address.getHostName());
		System.out.println(address.getCanonicalHostName());
	}
}
