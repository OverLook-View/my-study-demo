package com.example.sy.netty.channelHandle;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * \* User: admin
 * \* Date: 2018/5/24 15:39
 * \* Description:
 * \
 */
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //不需做特别的释放资源的动作,SimpleChannelInboundHandler 会自动释放资源，而无需存储任何信息的引用。
    }
}
