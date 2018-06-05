package com.example.sy.netty.bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;

/**
 * \* User: admin
 * \* Date: 2018/6/4 15:00
 * \* Description:
 * \
 */
public class CloseNettyTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(group);
        Future<?> future = group.shutdownGracefully();
        future.sync();
    }
}
