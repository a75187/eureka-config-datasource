package com.fxmm.custmoer.fxmmcustmoerserver.api;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc: -.
 * @Author: lipei
 * @CreateDate: 2019/4/28 10:50
 * @Version: 1.0
 */

public class ThreadLocalTest {
    static ThreadLocal<AtomicInteger> sequencer = ThreadLocal.withInitial(() -> new AtomicInteger(0));
    static class Task implements Runnable {
        @Override
        public void run() {
            int initial = sequencer.get().getAndIncrement();
            // 期望初始为0
            System.out.println(initial);
        //    sequencer.remove();
        }
    }
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);

    /*
    ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Task());
        executor.execute(new Task());
        executor.execute(new Task());
        executor.execute(new Task());
        executor.execute(new Task());
        executor.execute(new Task());
        executor.shutdown();
    */
    }

}
