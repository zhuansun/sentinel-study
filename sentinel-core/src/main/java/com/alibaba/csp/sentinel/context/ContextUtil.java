package com.alibaba.csp.sentinel.context;

/**
 * @author : zhuansun
 * @date : 2020-08-17 21:22
 **/
public class ContextUtil {

    /**
     * todo 先不关注context是怎么初始化的，以及context的作用是什么
     */
    static {
        // Cache the entrance node for default context.
        initDefaultContext();
    }

    private static void initDefaultContext() {



    }

    private static ThreadLocal<Context> contextHolder = new ThreadLocal<>();

    public static Context getContext() {
        return contextHolder.get();
    }

}
