package com.alibaba.csp.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 从概念上讲，需要保护的物理或逻辑资源应由一个条目Entry包围。
 *
 * 如果超过任何规则的阈值时，则满足拒绝的条件，对此资源的请求将被阻止，
 *
 * 一旦被阻止，将抛出BlockException。
 *
 * SphU： sentinel protected helper user
 *
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:44
 **/
public class SphU {

    /**
     * todo 这个又是干啥的呢？？
     */
    private static final Object[] OBJECTS0 = new Object[0];

    /**
     * 申请key这个条目，如果能申请到，表示key这个条目Entry没有被限流，如果不能申请到（抛出异常），表示被限流了。
     */
    public static Entry entry(String key) throws BlockException {
        //todo 考虑一下Env的作用是什么？？
       return Env.sph.entry(key,EntryType.OUT,1,OBJECTS0);
    }
}
