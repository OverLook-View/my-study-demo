package com.example.sy.netty.junit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/6/5 13:33
 * \* Description:
 * \
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength) {
        if (frameLength<=0){
            throw new IllegalArgumentException();
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()>=frameLength){
            ByteBuf byteBuf = in.readBytes(frameLength);
            out.add(byteBuf);
        }
    }
}
