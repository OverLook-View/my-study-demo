package com.example.sy.netty.junit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

/**
 * \* User: admin
 * \* Date: 2018/6/5 15:55
 * \* Description:
 * \
 */
public class TestFrameChunkDecoder {
    @Test
    public void testFrameChunkDecoder(){
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        Assert.assertTrue(channel.writeInbound(input.readBytes(2)));
        try {
            channel.writeInbound(input.readBytes(4));
            Assert.fail();
        }catch (TooLongFrameException e){

        }
        Assert.assertTrue(channel.writeInbound(input.readBytes(3)));

        Assert.assertTrue(channel.finish());

        channel.readInbound();
    }
}
