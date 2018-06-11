package com.example.sy.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class LogEventMonitor {

    private final Bootstrap bootstrap;
    private final EventLoopGroup group;

    public LogEventMonitor(InetSocketAddress inetSocketAddress) {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new LogEventDecoder());
                        pipeline.addLast(new LogEventHandler());
                    }
                }).localAddress(inetSocketAddress);
    }

    public Channel bind() {
        return bootstrap.bind().syncUninterruptibly().channel();
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        LogEventMonitor logEventMonitor = new LogEventMonitor(new InetSocketAddress(8080));
        try {
            Channel channel = logEventMonitor.bind();
            System.out.println("LogEventMonitor running");
            channel.closeFuture().await();
        } finally {
            logEventMonitor.stop();
        }

    }
}
