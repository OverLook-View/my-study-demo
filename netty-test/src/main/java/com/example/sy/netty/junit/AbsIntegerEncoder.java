package com.example.sy.netty.junit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/6/5 14:48
 * \* Description:
 * \
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        while (msg.readableBytes() >= 4) {
            int abs = Math.abs(msg.readInt());
            out.add(abs);
        }
    }
}
