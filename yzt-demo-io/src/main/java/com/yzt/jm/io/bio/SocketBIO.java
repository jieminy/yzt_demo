package com.yzt.jm.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-04 20:27
 */
@Slf4j
public class SocketBIO {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000, 20);
        log.info("【server started】 port 9000 ");
        AtomicInteger cnt = new AtomicInteger(0);
        while (true) {
            //阻塞1
            Socket client = server.accept();
            int threadCnt = cnt.getAndIncrement();
            log.info("【client {} on】", threadCnt);
            new Thread(() -> {
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        //阻塞2
                        String dataLine = reader.readLine();
                        if (dataLine != null) {
                            System.out.println(dataLine);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    log.info("【client {} off】", threadCnt);
                }

            }).start();

        }
    }
}
