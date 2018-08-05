package com.example.sy.netty.scheduleTask;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * \* User: admin
 * \* Date: 2018/6/20 16:51
 * \* Description:
 * \
 */
public class SchedulTaskTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);//新建 ScheduledExecutorService 使用10个线程
        ScheduledFuture<?> future = executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Now it is 60 seconds later");
            }
        }, 60, TimeUnit.SECONDS);//调度任务60秒后执行

        executor.shutdown();//关闭 ScheduledExecutorService 来释放任务完成的资源
    }

//    @Test
    public static void main1(String[] args) {
        Channel channel= null;
        io.netty.util.concurrent.ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Now it is 30 seconds later");
            }
        }, 30, TimeUnit.SECONDS);//调度任务60秒后执行

    }

    public static void main2(String[] args) {
        Channel channel= null;
        io.netty.util.concurrent.ScheduledFuture<?> future = channel.eventLoop().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("run every 30 seconds");
            }
        }, 30,30, TimeUnit.SECONDS);//调度任务30秒后每30秒执行
        future.cancel(false);//取消任务
    }


}
