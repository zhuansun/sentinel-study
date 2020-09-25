package com.alibaba.csp.sentinel.util;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhuansun
 * @date : 2020-08-17 20:32
 **/
public final class TimeUtil {
    private static volatile long currentTimeMills;


    static {
        currentTimeMills = System.currentTimeMillis();
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    currentTimeMills = System.currentTimeMillis();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    }catch (Throwable e){

                    }
                }
            }
        });
        daemon.setDaemon(true);
        daemon.setName("sentinel-time-tick-thread");
        daemon.start();
    }

    public static long currentTimeMills(){return currentTimeMills;}

}
