package com.example.sy.netty.channelHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * \* User: admin
 * \* Date: 2018/5/29 15:09
 * \* Description:
 * \
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {
    private final SslContext sslContext;
    private final boolean client;

    public HttpsCodecInitializer(SslContext sslContext, boolean client) {
        this.sslContext = sslContext;
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine sslEngine = sslContext.newEngine(ch.alloc());
        pipeline.addLast("ssl", new SslHandler(sslEngine));//添加 SslHandler 到 pipeline 来启用 HTTPS
        if (client) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
