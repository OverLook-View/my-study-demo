package com.example.sy.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/5/28 10:30
 * \* Description:
 * \
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
