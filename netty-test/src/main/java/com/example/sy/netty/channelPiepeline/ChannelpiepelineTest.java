package com.example.sy.netty.channelPiepeline;

import com.example.sy.netty.channelHandler.SimpleDiscardHandler;
import io.netty.channel.ChannelPipeline;

/**
 * \* User: admin
 * \* Date: 2018/5/25 10:06
 * \* Description:
 * \
 */
public class ChannelpiepelineTest {

    public static void main(String[] args) {
        ChannelPipeline channelPipeline=null;
        SimpleDiscardHandler handler1 = new SimpleDiscardHandler();
        channelPipeline.addLast("handler1",handler1);
        channelPipeline.addFirst("handler2",new SimpleDiscardHandler());

        channelPipeline.removeFirst();
        channelPipeline.removeLast();
        channelPipeline.remove("handler1");
        channelPipeline.remove(handler1);

        channelPipeline.replace("handler2","handler4",new SimpleDiscardHandler());
    }
}
