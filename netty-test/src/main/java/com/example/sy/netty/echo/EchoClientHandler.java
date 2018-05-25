package com.example.sy.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * \* User: admin
 * \* Date: 2018/5/21 10:26
 * \* Description:
 * \
 */
@ChannelHandler.Sharable//@Sharable标记这个类的实例可以在 channel 里共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    //服务器的连接被建立后调用
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        ByteBuf buffer = ctx.alloc().buffer();
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    //从服务器接受到数据后调用
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    //捕获异常调用
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
