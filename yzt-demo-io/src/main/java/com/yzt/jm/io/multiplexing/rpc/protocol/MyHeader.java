package com.yzt.jm.io.multiplexing.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:25
 */
@Data
public class MyHeader implements Serializable {
    private int flag;
    private long requestId;
    private long dataLen;

}
