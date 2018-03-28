package com.example.sy.zookeeper.zookeeperdemo;

import com.example.sy.zookeeper.zookeeperdemo.zkclient.ZKClientTest;
import com.example.sy.zookeeper.zookeeperdemo.zookeeper.ZeekeeperTest;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.KeeperException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ZookeeperDemoApplication {

	public static void main(String[] args) throws InterruptedException, KeeperException, IOException {
		SpringApplication.run(ZookeeperDemoApplication.class, args);

//		ZeekeeperTest zeekeeperTest = new ZeekeeperTest();
//		zeekeeperTest.connect();
//		zeekeeperTest.ZKOperations();
//		zeekeeperTest.close();

		ZKClientTest.testMethod();
	}
}
