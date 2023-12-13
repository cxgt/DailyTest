package com.example.dailytest.demos.Test;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/12 15:13
 */
public class TestAssert {
    public static void main(String[] args) {
        int a =1;
        System.out.println(a <<= 3);
    }
    /*
        //期待obj为null
        Assert.isNull(Object obj, String msg);
        Assert.isNull(Object obj, Supplier<String> msg);

        //期待不为null
        Assert.notNull(Object obj, String msg);
        Assert.notNull(Object obj, Supplier<String> msg);

        //期待数组|集合不为空
        Assert.notEmpty(Object[] arr, String msg);
        Assert.notEmpty(Object[] arr, Supplier<String> msg);

        //期待数组、集合中没有为null的元素（允许数组、集合自身为null）
        Assert.noNullElements(Object[] arr, String msg);
        Assert.noNullElements(Object[] arr, Supplier<String> msg);

        //期待str不包含指定子串substr，包含时抛出 IllegalArgumentException(msg)，msg可以用String或Supplier指定
        Assert.doesNotContain(String str, String substr, String msg);
        Assert.doesNotContain(String str, String substr, Supplier<String> msg);

        //期待str非空（相当于isNotEmpty)
        Assert.hasLength(String str, String msg);
        Assert.hasLength(String str, Supplier<String> msg);

        //期待str有内容（在str非空的基础上，要求字符串有空格之外的字符，相当于isNotBlank）
        Assert.hasText(String str, String msg);
        Assert.hasText(String str, Supplier<String> msg);

        //期待superType是subType的父类（不要求是直接父类）
        Assert.isAssignable(Class superType, Class subType);
        Assert.isAssignable(Class superType, Class subType, String msg);
        Assert.isAssignable(Class superType, Class subType, Supplier<String> msg);

        //期待obj是指定类的实例
        Assert.isInstanceOf(Class clazz, Object obj);
        Assert.isInstanceOf(Class clazz, Object obj, String msg);
        Assert.isInstanceOf(Class clazz, Object obj, Supplier<String> msg);

        //期待expression为true
        Assert.isTrue(boolean expression, String msg);
        Assert.isTrue(boolean expression, Supplier<String> msg);
     */
}
