package com.example.sy.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * \* User: admin
 * \* Date: 2018/5/18 17:17
 * \* Description:
 * \
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        super();
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
//            return;
//        }
//        int port = Integer.parseInt(args[0]);
//        new EchoServer(port).start();
        new EchoServer(9999).start();
    }

    private void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)//指定使用 NIO 的传输 Channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {//添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            ChannelFuture f = serverBootstrap.bind().sync();//绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();//关闭 channel 和 块，直到它被关闭
        } finally {
            group.shutdownGracefully().sync();//关机的 EventLoopGroup，释放所有资源。
        }
    }
}
