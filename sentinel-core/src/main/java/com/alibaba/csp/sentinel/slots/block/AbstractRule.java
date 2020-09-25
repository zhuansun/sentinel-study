package com.alibaba.csp.sentinel.slots.block;

/**
 * @author : zhuansun
 * @date : 2020-08-17 20:24
 **/
public abstract class AbstractRule implements Rule{

    /**
     * 每一个规则都要指定对哪个资源生效
     */
    private String resource;


    /**
     * Application name that will be limited by origin.
     * The default limitApp is {@code default}, which means allowing all origin apps.
     * 多个可以用逗号分割 (',').
     */
    private String limitApp;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getLimitApp() {
        return limitApp;
    }

    public void setLimitApp(String limitApp) {
        this.limitApp = limitApp;
    }
}
