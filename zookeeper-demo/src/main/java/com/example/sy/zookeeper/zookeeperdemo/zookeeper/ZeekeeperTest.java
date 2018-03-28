package com.example.sy.zookeeper.zookeeperdemo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZeekeeperTest {
    // 会话超时时间，设置为与系统默认时间一致
    private static final int SESSION_TIMEOUT = 30 * 1000;
    // 创建Zookeeper
    private ZooKeeper zk;
    // 创建watcher
    private Watcher watcher = new Watcher() {
        // 监听事件
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("WatcherEvent >>> " + watchedEvent.toString());
        }
    };

    public void connect() throws IOException {
        zk = new ZooKeeper("192.168.40.103:2181", this.SESSION_TIMEOUT, this.watcher);
    }

    public void ZKOperations() throws KeeperException, InterruptedException {
        // 创建节点 znode ： znode, 数据： znode data ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent
        String znodeString = "znode data";
        String path = "/myZnode/1";
        zk.create(path, znodeString.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 检查节点
        Stat znode = zk.exists(path, false);
        System.out.println("is exists? " + znode.toString());

        // 获取节点
        byte[] data = zk.getData(path, this.watcher, null);
        System.out.println("data: " + new String(data));

        // 修改节点
        System.out.println("修改第一次");
        zk.setData(path, "znode data2".getBytes(), -1);

        // 再次修改节点不触发watcher ，所谓的“一次性触发”
        System.out.println("修改第二次");
        zk.setData(path, "znode data3".getBytes(), -1);

        // 获取节点
        System.out.println(new String(zk.getData(path, this.watcher, null)));

        // 删除节点
        zk.delete(path, -1);

        // 检查是否删除
        System.out.println("is exists? " + zk.exists(path, false));
    }

    public void close() throws InterruptedException {
        zk.close();
    }
}
