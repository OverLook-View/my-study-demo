package com.example.sy.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogEvent logEvent) throws Exception {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(logEvent.getReceived());
        stringBuilder.append(" [");
        stringBuilder.append(logEvent.getSource().toString());
        stringBuilder.append("] [");
        stringBuilder.append(logEvent.getLogFile());
        stringBuilder.append("]:");
        stringBuilder.append(logEvent.getMsg());

        System.out.println(stringBuilder.toString());
    }
}
