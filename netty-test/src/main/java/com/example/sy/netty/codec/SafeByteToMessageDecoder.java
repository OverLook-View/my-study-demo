package com.example.sy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/5/28 10:44
 * \* Description:
 * \
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
    private static final int MAX_FREAM_SIZE = 1024;

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        if (readable > MAX_FREAM_SIZE) {
            in.skipBytes(readable);
            throw new TooLongFrameException("Frame too big");
        }
        //do something
    }
}
