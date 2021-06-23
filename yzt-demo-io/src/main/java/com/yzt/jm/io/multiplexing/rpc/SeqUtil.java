package com.yzt.jm.io.multiplexing.rpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-13 18:29
 */
public class SeqUtil {
    public static byte[] objToBytes(Object obj) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(obj);
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("object to bytes error");
    }
}
