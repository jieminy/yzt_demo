package com.yzt.jm.sync.lock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: jiemin
 * @date: 2021-01-25 16:39
 */
@Slf4j
public class CyclicBarrierDemo {
    //派单队列
    Vector<P> ps = new Vector<>();
    //订单队列
    Vector<O> os = new Vector<>();

    Executor executor = Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier = new CyclicBarrier(2, ()-> executor.execute(this::check));

    private void check(){
        P p = ps.remove(0);
        O o = os.remove(0);

        //对账
        log.info("对账中>>>{}", p.getName());
        //落库
        log.info("落库>>>{}", o.getName());
    }

    public void checkAll(){
        Vector<P> ps_db = new Vector<>();
        ps_db.add(new P("订单1"));
        ps_db.add(new P("订单2"));
        Thread t1 = new Thread(()->{
            try {
                while(ps_db.size() != 0)
                {
                    ps.add(ps_db.remove(0));
                    barrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Vector<O> os_db = new Vector<>();
        os_db.add(new O("派单1"));
        os_db.add(new O("派单2"));
        Thread t2 = new Thread(()->{
            try {
                while (os_db.size() != 0){
                   os.add(os_db.remove(0));
                   barrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }

    public static void main(String [] args){
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.checkAll();
    }


    @Data
    private class P{
        private String name;
        private P(String name){
            this.name = name;
        }
    }
    @Data
    private class O{
        private String name;
        private O(String name){
            this.name = name;
        }
    }
}
