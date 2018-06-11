package com.example.sy.netty.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        ByteBuf data = datagramPacket.content();//获取 DatagramPacket 中数据的引用
        int i = data.indexOf(0, data.readableBytes(), LogEvent.SEPARTOR);//获取 SEPARATOR 的索引
        String fileName = data.slice(0, i).toString(CharsetUtil.UTF_8);//从数据中读取文件名
        String logMsg = data.slice(i+1, data.readableBytes()).toString(CharsetUtil.UTF_8);//读取数据中的日志消息
        LogEvent logEvent=new LogEvent(datagramPacket.recipient(),logMsg,fileName,System.currentTimeMillis());//构造新的 LogEvent 对象并将其添加到列表中
        list.add(logEvent);
    }
}
