package com.alibaba.csp.sentinel;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.context.NullContext;
import com.alibaba.csp.sentinel.slotchain.ProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.slotchain.StringResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * sph: 获取【受保护资源】的接口的唯一一个实现类
 * <p>
 * 算是sentinel的统一入口； 通过这个类进行后续核心代码的调用；
 * <p>
 * Center txxx Sentinel Protected Hepler(中心处理器)
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:50
 **/
public class CtSph implements Sph {

    /**
     * 具体的申请获取条目
     */
    @Override
    public Entry entry(String name, EntryType type, int count, Object... args)
        throws BlockException {
        //封装成资源对象
        StringResourceWrapper resource = new StringResourceWrapper(name, type);
        return entry(resource, count, args);
    }

    /**
     * Do all Rules checking about the resource.
     * <p>
     * 每个不同的resource将会使用ProcessorSlot进行规则检查，相同的资源使用同一个ProcessorSlot
     */
    @Override
    public Entry entry(ResourceWrapper resource, int count, Object... args) throws BlockException {
        return entryWithPriority(resource, count, false, args);
    }

    private Entry entryWithPriority(ResourceWrapper resourceWrapper, int count, boolean prioritized, Object[] args) throws BlockException {

        // todo Context的作用是什么呢？？
        Context context = ContextUtil.getContext();
        if (context instanceof NullContext) {
            // NullContext 表示上下文数量已超过阈值， 那么NullContext的作用是什么呢？？
            //因此这里仅初始化条目。 不会进行任何规则检查。
            //todo 为什么呢？？？
            //todo NullContext的作用是什么呢？？
            //如果是 NullContext，那么说明 context name 超过了 2000 个，参见 ContextUtil#trueEnter
            //这个时候，Sentinel 不再接受处理新的 context 配置，也就是不做这些新的接口的统计、限流熔断等

            //因为这种情况，不进行限流熔断，所以直接返回条目对象，表示请求通过
            return new CtEntry(resourceWrapper, null, context);
        }

        if (context == null) {
            // 使用默认的context
            context = InternalContextUtil.internalEnter(Constants.CONTEXT_DEFAULT_NAME);
        }

        // Global switch is close, no rule checking will do.
        // Sentinel 的全局开关，Sentinel 提供了接口让用户可以在 dashboard 开启/关闭
        // 默认是true，就不会走到这个判断里
        if (!Constants.ON) {
            return new CtEntry(resourceWrapper, null, context);
        }

        //根据包装过的资源对象获取对应的SlotChain
        ProcessorSlot<Object> chain = lookProcessChain(resourceWrapper);

        if (chain == null) {
            return new CtEntry(resourceWrapper, null, context);
        }

        CtEntry ctEntry = new CtEntry(resourceWrapper, chain, context);

        try {
            // 执行Slot的entry方法
            chain.entry(context, resourceWrapper, null, count, prioritized, args);
        } catch (BlockException e1) {
            //如果上层方法捕获了BlockException，则说明请求被限流了，否则请求能正常执行
            ctEntry.exit(count, args);
            //如果SlotChain的entry方法抛出了BlockException，则将该异常继续向上抛出
            throw e1;
        } catch (Throwable e1) {
            // 这个异常一般不会发生，除非sentinel系统内部出现故障
            //RecordLog.info("Sentinel unexpected exception", e1);
            System.out.println("Sentinel unexpected exception"+e1.getMessage());
        }

        //如果SlotChain的entry方法正常执行了，则最后会将该entry对象返回
        return ctEntry;

    }

    /**
     * 根据资源对象获取判断的责任链
     */
    private ProcessorSlot<Object> lookProcessChain(ResourceWrapper resourceWrapper) {

    }

    private final static class InternalContextUtil extends ContextUtil {
        static Context internalEnter(String name) {
            return trueEnter(name, "");
        }

        static Context internalEnter(String name, String origin) {
            return trueEnter(name, origin);
        }
    }

}
