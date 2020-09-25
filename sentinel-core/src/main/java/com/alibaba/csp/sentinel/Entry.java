package com.alibaba.csp.sentinel;

import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.util.TimeUtil;

/**
 *
 * 条目抽象类
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:02
 **/
public abstract class Entry implements AutoCloseable{

    /**
     * todo 为什么条目退出要有一个OBJECTS0
     */
    private static final Object[] OBJECTS0 = new Object[0];

    /**
     * 一个条目要指定是哪个资源
     */
    protected ResourceWrapper resourceWrapper;

    /**
     * 条目的创建时间，构造方法中赋值，取的系统当前时间
     */
    private long createTime;


    @Override
    public void close() throws Exception {
        exit();
    }

    public void exit() throws ErrorEntryFreeException {
        /**
         * todo 为什么条目退出要有一个OBJECTS0
         */
        exit(1, OBJECTS0);
    }

    public abstract void exit(int count, Object... args) throws ErrorEntryFreeException;


    public Entry(ResourceWrapper resourceWrapper) {
        this.resourceWrapper = resourceWrapper;
        this.createTime = TimeUtil.currentTimeMills();
    }

}
