package com.example.sy.netty.memcache;

import java.util.Random;

/**
 * \* User: admin
 * \* Date: 2018/6/20 15:44
 * \* Description:
 * \
 */
public class MemCachedRequest {
    private static final Random random=new Random();
    private final int magic=0x80;
    private final byte opcode;
    private final String key;
    private final int flags=0xdeadbeef;
    private final int expires;
    private final String body;
    private final int id=random.nextInt();
    private final long cas=0;
//    private final boolean hasExtras;

    public MemCachedRequest(byte opcode, String key,  String body) {
        this.opcode = opcode;
        this.key = key;
        this.expires = 0;
        this.body = body==null?"":body;
//        this.hasExtras = opcode==Opcode.SET;
    }
}
