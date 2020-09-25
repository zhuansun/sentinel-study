package com.alibaba.csp.sentinel.slotchain;

import com.alibaba.csp.sentinel.context.Context;

/**
 * @author : zhuansun
 * @date : 2020-08-20 21:30
 **/
public interface ProcessorSlot<T> {

    void entry(Context context, ResourceWrapper resourceWrapper, T params, int count,
        boolean prioritized,
        Object... args) throws Throwable;
}
