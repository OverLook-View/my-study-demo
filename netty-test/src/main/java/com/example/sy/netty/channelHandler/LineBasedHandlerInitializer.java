package com.example.sy.netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * \* User: admin
 * \* Date: 2018/5/29 16:47
 * \* Description:
 * \
 */
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LineBasedFrameDecoder(65*1024));//添加一个 LineBasedFrameDecoder 用于提取帧并把数据包转发到下一个管道中的处理程序,在这种情况下就是 FrameHandler
        pipeline.addLast(new FrameHandler());//添加 FrameHandler 用于接收帧
    }
    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf>{
        @Override
        //每次调用都需要传递一个单帧的内容
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        }
    }
}
