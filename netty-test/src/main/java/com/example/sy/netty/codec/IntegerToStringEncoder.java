package com.example.sy.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/5/28 11:24
 * \* Description:
 * \
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
