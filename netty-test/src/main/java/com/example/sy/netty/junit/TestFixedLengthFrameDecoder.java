package com.example.sy.netty.junit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

/**
 * \* User: admin
 * \* Date: 2018/6/5 13:44
 * \* Description:
 * \
 */
public class TestFixedLengthFrameDecoder {
    @Test
    public void testFrameDecoded(){
        ByteBuf buffer = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buffer.writeByte(i);
        }
        ByteBuf input = buffer.duplicate();
        EmbeddedChannel channel=new EmbeddedChannel(new FixedLengthFrameDecoder(3));//新增 EmbeddedChannel 并添加 FixedLengthFrameDecoder 用于测试

        Assert.assertFalse(channel.writeInbound(input.readBytes(2)));//写数据到 EmbeddedChannel
        Assert.assertTrue(channel.writeInbound(input.readBytes(7)));

        Assert.assertTrue(channel.finish());//标记 channel 已经完成
        ByteBuf read=channel.readInbound();
        Assert.assertEquals(buffer.readSlice(3),read);
        read.release();

        read=channel.readInbound();
        Assert.assertEquals(buffer.readBytes(3),read);
        read.release();

        read=channel.readInbound();
        Assert.assertEquals(buffer.readBytes(3),read);
        read.release();

        Assert.assertNull(channel.readInbound());
        buffer.release();
    }

    @Test
    public void testFrameDecoder2(){

    }
}
