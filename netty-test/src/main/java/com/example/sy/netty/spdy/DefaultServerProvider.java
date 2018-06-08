package com.example.sy.netty.spdy;

import org.eclipse.jetty.npn.NextProtoNego;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * \* User: admin
 * \* Date: 2018/6/8 15:19
 * \* Description:
 * \
 */
public class DefaultServerProvider implements NextProtoNego.ServerProvider {

    private static final List<String> PROTOCOLS = Collections.unmodifiableList(Arrays.asList("spdy/2", "spdy/3", "http/1.1"));//定义所有的 ServerProvider 实现的协议
    private String protocol;

    public void unsupported() {
        protocol = "http/1.1";//设置如果 SPDY 协议失败了就转到 http/1.1
    }

    public List<String> protocols() {
        return PROTOCOLS;//返回支持的协议的列表
    }

    public void protocolSelected(String protocol) {
        this.protocol = protocol;//设置选择的协议
    }

    public String getSelectedProtocol() {
        return protocol;//返回选择的协议
    }
}
