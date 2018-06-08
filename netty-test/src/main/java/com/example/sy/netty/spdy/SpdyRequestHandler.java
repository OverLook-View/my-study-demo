package com.example.sy.netty.spdy;

import io.netty.channel.ChannelHandler;

/**
 * \* User: admin
 * \* Date: 2018/6/8 17:19
 * \* Description:
 * \
 */
@ChannelHandler.Sharable
public class SpdyRequestHandler extends HttpRequestHandler {
    @Override
    protected String getContent() {
        return "This connect is transmitted via SPDY\r\n";
    }
}
