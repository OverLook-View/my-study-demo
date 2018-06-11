package com.example.sy.netty;


import io.netty.channel.socket.SocketChannel;

/**
 * \* User: admin
 * \* Date: 2018/5/16 15:14
 * \* Description:
 * \
 */
public class ChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("Decoder",new Decoder());
        ch.pipeline().addLast("Server",new ServerHandler());
    }
}
