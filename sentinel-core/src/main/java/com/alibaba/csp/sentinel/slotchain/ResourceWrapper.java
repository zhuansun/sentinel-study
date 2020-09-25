package com.alibaba.csp.sentinel.slotchain;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.util.AssertUtil;

/**
 *
 * A wrapper of resource name and type.
 * @author : zhuansun
 * @date : 2020-08-17 21:10
 **/
public abstract class ResourceWrapper {

    /**
     * 资源的名字
     */
    protected final String name;
    /**
     * 条目的类型  IN or  OUT
     */
    protected final EntryType entryType;

    /**
     * 资源的类型
     */
    protected final int resourceType;

    public ResourceWrapper(String name, EntryType entryType, int resourceType) {
        AssertUtil.notEmpty(name, "resource name cannot be empty（资源名字不能为空）");
        AssertUtil.notNull(entryType, "entryType cannot be null（条目类型不能为空）");
        this.name = name;
        this.entryType = entryType;
        this.resourceType = resourceType;
    }

    /**
     * 打印出漂亮的资源信息
     */
    public abstract String getShowName();





}
