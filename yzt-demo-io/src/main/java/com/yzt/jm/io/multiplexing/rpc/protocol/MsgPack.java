package com.yzt.jm.io.multiplexing.rpc.protocol;

import lombok.Data;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-13 14:12
 */
@Data
public class MsgPack {
    private MyHeader header;
    private MyBody body;

    public MsgPack(MyHeader header, MyBody body) {
        this.header = header;
        this.body = body;
    }
}
