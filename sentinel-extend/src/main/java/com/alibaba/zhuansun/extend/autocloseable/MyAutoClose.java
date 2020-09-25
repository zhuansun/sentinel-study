package com.alibaba.zhuansun.extend.autocloseable;

/**
 * 测试autoClose
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:08
 **/
public class MyAutoClose implements AutoCloseable{


    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClose....调用close方法.........");
    }
}
