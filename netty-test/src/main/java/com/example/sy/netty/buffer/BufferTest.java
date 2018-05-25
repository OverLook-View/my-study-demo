package com.example.sy.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * \* User: admin
 * \* Date: 2018/5/22 14:33
 * \* Description:
 * \
 */
public class BufferTest {
    public static void main(String[] args) {

        //HEAP BUFFER(堆缓冲区)
        ByteBuf byteBuf = null;
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();//得到引用数组
            int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();//计算第一字节的偏移量
            int length = byteBuf.readableBytes();//获取可读字节数
//            handleArray(array,offset,length);
        }

        //DIRECT BUFFER(直接缓冲区)
        if (!byteBuf.hasArray()) {
            int length = byteBuf.readableBytes();//获取可读字节数
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);//字节复制到数组
//            handleArray(array, 0, length);
        }

        //COMPOSITE BUFFER(复合缓冲区)
        ByteBuffer header = null;
        ByteBuffer body = null;
        ByteBuffer[] message = {header, body};
        ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header);
        message2.put(body);
        message2.flip();

        ByteBuf headBuf = null;
        ByteBuf bodyBuf = null;
        CompositeByteBuf compositeByteBuf = null;
        compositeByteBuf.addComponents(headBuf, bodyBuf);
        compositeByteBuf.removeComponent(0);
        for (int i = 0; i < compositeByteBuf.numComponents(); i++) {
            System.out.println(compositeByteBuf.component(i).toString());
        }

        int length = compositeByteBuf.readableBytes();
        byte[] bytes = new byte[length];
        compositeByteBuf.getBytes(compositeByteBuf.readerIndex(), bytes);
//        handleArray(bytes,0,length);
    }
}
