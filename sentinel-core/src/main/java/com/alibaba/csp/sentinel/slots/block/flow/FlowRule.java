package com.alibaba.csp.sentinel.slots.block.flow;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.node.DefaultNode;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;

/**
 * 流控规则
 * @author : zhuansun
 * @date : 2020-08-17 20:24
 **/
public class FlowRule extends AbstractRule {

    /**
     * 流控类型：QPS，还是线程数 (0: thread count, 1: QPS).
     */
    private int grade = RuleConstant.FLOW_GRADE_QPS;


    /**
     * 流控阈值threshold count.
     */
    private double count;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public boolean passCheck(Context context, DefaultNode node, int count, Object... args) {
        return false;
    }
}
