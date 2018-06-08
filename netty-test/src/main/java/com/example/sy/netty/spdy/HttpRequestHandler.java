package com.example.sy.netty.spdy;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * \* User: admin
 * \* Date: 2018/6/8 16:08
 * \* Description:
 * \
 */
@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (HttpHeaders.is100ContinueExpected(msg)) {
            send100Cotinue(ctx);//检查如果接下来的响应是预期的，就写入
        }

        FullHttpResponse response = new DefaultFullHttpResponse(msg.getProtocolVersion(), HttpResponseStatus.OK);//新建 FullHttpResponse,用于对请求的响应
        response.content().writeBytes(getContent().getBytes(CharsetUtil.UTF_8));//生成响应的内容，将它写入 payload
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");//设置头文件，这样客户端就能知道如何与 响应的 payload 交互

        boolean keepAlive = HttpHeaders.isKeepAlive(msg);
        if (keepAlive) {//检查请求设置是否启用了 keepalive;如果是这样,将标题设置为符合HTTP RFC
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        ChannelFuture future = ctx.writeAndFlush(response);//写响应给客户端，并获取到 Future 的引用，用于写完成时，获取到通知

        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);//如果响应不是 keepalive，在写完成时关闭连接
        }
    }

    protected String getContent() {//返回内容作为响应的 payload
        return "This content is transmitted via HTTP\r\n";
    }

    private static void send100Cotinue(ChannelHandlerContext ctx) {//Helper 方法生成了100 持续的响应，并写回给客户端
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {//若执行阶段抛出异常，则关闭管道
        cause.printStackTrace();
        ctx.close();
    }
}
