package com.example.sy.netty.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * \* User: admin
 * \* Date: 2018/5/29 11:13
 * \* Description:
 * \
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<IntegerToStringDecoder, IntegerToStringEncoder> {
    public CombinedByteCharCodec() {
        super(new IntegerToStringDecoder(), new IntegerToStringEncoder());
    }
}
