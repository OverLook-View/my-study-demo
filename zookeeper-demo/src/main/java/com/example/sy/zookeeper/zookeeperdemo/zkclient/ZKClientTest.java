package com.example.sy.zookeeper.zookeeperdemo.zkclient;

import org.I0Itec.zkclient.*;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZKClientTest {

    // 订阅children变化
    public static void childChangeListener(ZkClient zkClient, final String path) {
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("children of path " + s + " : " + list);
            }
        });
    }

    public static void dataChangeListener(ZkClient zkClient, final String path) {
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("data of " + s + " has changed.");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("data of " + s + " has deleted.");
            }
        });
    }

    public static void stateChangelistener(ZkClient zkClient) {
        zkClient.subscribeStateChanges(new IZkStateListener() {
            //状态改变
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("handle state changed.");
            }

            //新建会话
            @Override
            public void handleNewSession() throws Exception {
                System.out.println("handle new session.");
            }

            //会话建立错误
            @Override
            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                System.out.println("handle session establishment error.");
            }
        });
    }

    public static void testMethod() throws InterruptedException {
//        ZkClient zkClient = new ZkClient("192.168.40.103:2181");
        ZkClient zkClient = new ZkClient("10.1.3.107:2181");
//        String path = "/myZnode";
        String path = "/dubbo/com.bizideal.whoami.member.facade.OrgMemberUnitInfoWriteFacade/consumers";

//        childChangeListener(zkClient, path);
//        dataChangeListener(zkClient, path);
//        stateChangelistener(zkClient);

        List<String> children = zkClient.getChildren(path);
        System.out.println(children);

//        if (!zkClient.exists(path)) {
//            zkClient.createPersistent(path, "hello data1");
//        }
        String data = zkClient.readData(path);
        System.out.println(data);
//
//        zkClient.updateDataSerialized(path, new DataUpdater<String>() {
//
//            @Override
//            public String update(String s) {
//                return s + "-123";
//            }
//        });
//        System.out.println((String) zkClient.readData(path));

//        zkClient.delete(path);

        TimeUnit.SECONDS.sleep(3);
//        while (true){
//
//        }
    }
}
