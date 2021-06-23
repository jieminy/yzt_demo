package com.yzt.jm.io.multiplexing.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-10 20:25
 */
@Data
public class MyBody implements Serializable {
    private String name;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;
    private Object res;
}
