package com.example.sy.netty.junit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * \* User: admin
 * \* Date: 2018/6/5 14:51
 * \* Description:
 * \
 */
public class TestAbsIntegerEncoder {
    @Test
    public void testAbsIntegerEncoder() {
        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            byteBuf.writeInt(i * -1);
        }

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new AbsIntegerEncoder());
        Assert.assertTrue(embeddedChannel.writeOutbound(byteBuf));

        Assert.assertTrue(embeddedChannel.finish());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(i, embeddedChannel.readOutbound());
        }
        Assert.assertNull(embeddedChannel.readOutbound());
    }
}
