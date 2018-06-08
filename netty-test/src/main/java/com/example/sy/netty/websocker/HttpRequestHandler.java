package com.example.sy.netty.websocker;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * \* User: admin
 * \* Date: 2018/6/8 9:24
 * \* Description:
 * \
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String wsUrl;
    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException("Unable to location index.html", e);
        }
    }

    protected HttpRequestHandler(String wsUrl) {
        super();
        this.wsUrl = wsUrl;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (wsUrl.equalsIgnoreCase(msg.getUri())) {
            ctx.fireChannelRead(msg.retain());//如果请求是一次升级了的 WebSocket 请求，则递增引用计数器（retain）并且将它传递给在 ChannelPipeline 中的下个 ChannelInboundHandler
        } else {
            if (HttpHeaders.is100ContinueExpected(msg)) {
                send100Continue(ctx);//处理符合 HTTP 1.1的 "100 Continue" 请求
            }
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");//读取 index.html
            HttpResponse response = new DefaultHttpResponse(msg.getProtocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html;charset=UTF-8");
            boolean keepAlive = HttpHeaders.isKeepAlive(msg);

            if (keepAlive) {//判断 keepalive 是否在请求头里面
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            }
            ctx.write(response);//写 HttpResponse 到客户端

            if (ctx.pipeline().get(SslHandler.class) == null) {//写 index.html 到客户端，根据 ChannelPipeline 中是否有 SslHandler 来决定使用 DefaultFileRegion 还是 ChunkedNioFile
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);//写并刷新 LastHttpContent 到客户端，标记响应完成

            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);//如果 请求头中不包含 keepalive，当写完成时，关闭 Channel
            }
        }

    }

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
