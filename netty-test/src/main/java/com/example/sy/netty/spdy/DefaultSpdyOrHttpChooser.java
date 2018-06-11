package com.example.sy.netty.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.spdy.SpdyVersion;

public class DefaultSpdyOrHttpChooser /*extends SpdyOrHttpChooser*/ {
    protected DefaultSpdyOrHttpChooser(int maxSpdyContentLength, int maxHttpContentLength) {
//        super(maxSpdyContentLength,maxHttpContentLength);
    }

//    @Override
    protected void configureSpdy(ChannelHandlerContext channelHandlerContext, SpdyVersion spdyVersion) throws Exception {

    }

//    @Override
    protected void configureHttp1(ChannelHandlerContext channelHandlerContext) throws Exception {

    }
}
