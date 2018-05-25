package com.example.sy.netty.transport;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \* User: admin
 * \* Date: 2018/5/22 13:43
 * \* Description:
 * \
 */
public class TransportApiTest {
    public static void main(String[] args) {
        Channel channel = new NioServerSocketChannel();
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        ChannelFuture channelFuture = channel.writeAndFlush(buf);
        channelFuture.addListener(new ChannelFutureListener() { //写操作完成后通知
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Write successful");
                } else {
                    System.err.println("Write error");
                    future.cause().printStackTrace();
                }
            }
        });

        final Channel channel1=new NioServerSocketChannel();
        final ByteBuf byteBuf=Unpooled.copiedBuffer("Your data",CharsetUtil.UTF_8).retain();
        Runnable writer=new Runnable() {
            public void run() {
                channel1.writeAndFlush(byteBuf.duplicate());
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writer);
        executorService.execute(writer);

    }
}
