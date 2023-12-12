package com.example.dailytest.demos.Utils;

import com.example.dailytest.demos.Config.BkmpException;
import com.google.common.base.Preconditions;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/11 16:57
 */
// 封装代码:
public final class AssertUtils {
    /**
     * 检查条件表达式是否为真
     *
     * @param expression 条件表达式
     * @param msgTemplate 错误消息模板
     * @param vars 占位符对应变量
     * @throws BkmpException 条件表达式结果为假
     */
    public static void checkArgument(boolean expression, String msgTemplate,
                                     Object... vars) {
        try {
            Preconditions.checkArgument(expression);
        } catch (IllegalArgumentException e) {
            throw new BkmpException(e);
        }
    }
    /**
     * 检查条件表达式是否为假
     *
     * @param expression 条件表达式
     * @param msgTemplate 错误消息模板
     * @param vars 占位符对应变量
     * @throws BkmpException 条件表达式结果为假
     */
    public static void checkArgumentNotTrue(boolean expression, String msgTemplate,
                                            Object... vars) {
        try {
            Preconditions.checkArgument(!expression);
        } catch (IllegalArgumentException e) {
            throw new BkmpException(e);
        }
    }

    // 省略其他部分的封装
// 调用例子:
//AssertUtils.checkArgument(merchantEntity.exist(), ErrDetailEnum.DATA_NOT_EXIT, "商户不存在");
}

