package com.alibaba.csp.sentinel.slotchain;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.ResourceTypeConstants;

/**
 * 通用的string资源的包装类
 *
 * @author : zhuansun
 * @date : 2020-08-17 21:14
 **/
public class StringResourceWrapper extends ResourceWrapper{

    public StringResourceWrapper(String name, EntryType entryType) {
        super(name, entryType, ResourceTypeConstants.COMMON);
    }

    public StringResourceWrapper(String name, EntryType entryType, int resourceType) {
        super(name, entryType, resourceType);
    }

    @Override
    public String getShowName() {
        return name;
    }


    @Override
    public String toString() {
        return "StringResourceWrapper{" +
            "name='" + name + '\'' +
            ", entryType=" + entryType +
            ", resourceType=" + resourceType +
            '}';
    }

}
