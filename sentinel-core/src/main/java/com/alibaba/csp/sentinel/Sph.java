package com.alibaba.csp.sentinel;

import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 *
 * 获取【受保护的条目】的接口。
 * 如果满足任何【阻止】条件，这个类的子类将会抛出：BlockException。
 * 如果成功获取到【受保护的资源】 表示允许调用通过。
 *
 * Sph: sentinel protected helper
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:46
 **/
public interface Sph extends SphResourceTypeSupport{

    Entry entry(String key, EntryType out, int i, Object... args) throws BlockException;


    Entry entry(ResourceWrapper resource, int count,  Object... args) throws BlockException;

}
