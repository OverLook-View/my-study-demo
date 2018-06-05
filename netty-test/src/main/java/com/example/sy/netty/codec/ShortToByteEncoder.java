package com.example.sy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * \* User: admin
 * \* Date: 2018/5/28 11:10
 * \* Description:
 * \
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }
}
