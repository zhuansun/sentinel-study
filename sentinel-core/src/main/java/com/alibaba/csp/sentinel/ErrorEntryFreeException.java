package com.alibaba.csp.sentinel;

/**
 * 标识资源申请和资源退出时候出现的异常
 *
 * @author : zhuansun
 * @date : 2020-08-17 20:18
 **/
public class ErrorEntryFreeException extends RuntimeException {

    public ErrorEntryFreeException(String s) {
        super(s);
    }

}
