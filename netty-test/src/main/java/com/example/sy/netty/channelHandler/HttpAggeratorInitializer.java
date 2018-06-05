package com.example.sy.netty.channelHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.*;

/**
 * \* User: admin
 * \* Date: 2018/5/29 14:57
 * \* Description:
 * \
 */
public class HttpAggeratorInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpAggeratorInitializer(boolean client) {
        this.client = client;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (client) {
            pipeline.addLast("codec", new HttpClientCodec());
            pipeline.addLast("decompressor", new HttpContentDecompressor());//添加 HttpContentDecompressor 用于处理来自服务器的压缩的内容
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
            pipeline.addLast("compressor", new HttpContentCompressor());//HttpContentCompressor 用于压缩来自 client 支持的 HttpContentCompressor
        }
        pipeline.addLast("aggregator", new HttpObjectAggregator(512 * 1024));//添加 HttpObjectAggregator 到 ChannelPipeline, 使用最大消息值是 512kb
    }
}
