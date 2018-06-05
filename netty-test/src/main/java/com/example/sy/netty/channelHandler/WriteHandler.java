package com.example.sy.netty.channelHandler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * \* User: admin
 * \* Date: 2018/5/25 14:16
 * \* Description:
 * \
 */
public class WriteHandler extends ChannelHandlerAdapter {
    private ChannelHandlerContext ctx;
    public void handlerAdded(ChannelHandlerContext ctx){
        this.ctx=ctx;
    }
    public void send(String msg){
        ctx.write(msg);//使用之前存储的 ChannelHandlerContext 来发送消息
    }
}
