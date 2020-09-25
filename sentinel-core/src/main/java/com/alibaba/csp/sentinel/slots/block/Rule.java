package com.alibaba.csp.sentinel.slots.block;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.node.DefaultNode;

/**
 * Base interface of all rules.
 */
public interface Rule {

    /**
     * Check whether current statistical indicators meet this rule, which means not exceeding any threshold.
     * 检查当前的统计指标是否满足这个规格，即：不超过任何阈值
     * @param context 当前的上下文
     * @param node
     * @param count ？？？？
     * @param args  原始调用的参数
     * @return 当前的统计指标不超过任何阈值 返回true，否则返回 false
     */
    boolean passCheck(Context context, DefaultNode node, int count, Object... args);
}
