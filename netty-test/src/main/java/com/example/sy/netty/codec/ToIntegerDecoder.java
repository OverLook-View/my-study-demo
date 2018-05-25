package com.example.sy.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/5/25 16:18
 * \* Description:
 * \
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {//实现继承了 ByteToMessageDecode 用于将字节解码为消息
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()>=4){//检查可读的字节是否至少有4个 ( int 是4个字节长度)
            out.add(in.readInt());//从入站 ByteBuf 读取 int ， 添加到解码消息的 List 中
        }
    }
}
