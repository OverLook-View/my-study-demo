package com.example.sy.netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder;

/**
 * \* User: admin
 * \* Date: 2018/5/29 17:16
 * \* Description:
 * \
 */
public class CmdHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new CmdDecoder(65 * 1024));//添加一个 CmdDecoder 到管道；将提取 Cmd 对象和转发到在管道中的下一个处理器
        pipeline.addLast(new CmdHandler());//添加 CmdHandler 将接收和处理 Cmd 对象
    }

    public static final class Cmd {//命令也是 POJO
        private final ByteBuf name;
        private final ByteBuf args;

        public Cmd(ByteBuf name, ByteBuf args) {
            this.name = name;
            this.args = args;
        }

        public ByteBuf getName() {
            return name;
        }

        public ByteBuf getArgs() {
            return args;
        }
    }

    public static final class CmdDecoder extends LineBasedFrameDecoder {

        public CmdDecoder(int maxLength) {
            super(maxLength);
        }

        @Override
        protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
            ByteBuf frame = (ByteBuf) super.decode(ctx, buffer);//super.decode() 通过结束分隔从 ByteBuf 提取帧
            if (frame == null) {
                return null;//frame 是空时，则返回 null
            }
            int index = frame.indexOf(frame.readerIndex(), frame.writerIndex(), (byte) ' ');//找到第一个空字符的索引。首先是它的命令名；接下来是参数的顺序
            return new Cmd(frame.slice(frame.readerIndex(), index), frame.slice(index + 1, frame.writerIndex()));//从帧先于索引以及它之后的片段中实例化一个新的 Cmd 对象
        }
    }

    public static final class CmdHandler extends SimpleChannelInboundHandler<Cmd> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Cmd msg) throws Exception {
            //处理通过管道的 Cmd 对象
        }
    }
}
