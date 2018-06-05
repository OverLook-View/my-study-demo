package com.example.sy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/5/28 10:00
 * \* Description:
 * \
 */
public class RepalyingDecoder extends ReplayingDecoder {
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.readInt());
    }
}
