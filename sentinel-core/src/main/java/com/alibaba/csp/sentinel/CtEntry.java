package com.alibaba.csp.sentinel;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.ProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;

/**
 * 中心条目对象 中心处理器（CtSph）的处理结果都是中心条目对象（CtEntry）
 *
 * @author : zhuansun
 * @date : 2020-08-17 21:30
 **/
class CtEntry extends Entry{

    /**
     * 当前条目的校验责任链
     */
    protected ProcessorSlot<Object> chain;

    protected Context context;

    public CtEntry(ResourceWrapper resource, ProcessorSlot<Object> chain, Context context) {
        super(resource);

        this.chain = chain;
        this.context = context;

        //setUpEntryFor(context);

    }

    @Override
    public void exit(int count, Object... args) throws ErrorEntryFreeException {

    }
}
