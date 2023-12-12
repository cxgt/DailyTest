package com.example.dailytest.demos.Test;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/11 16:36
 */
public class TestPreconditions {
    public static void main(String[] args) {
        List<String> stringList=new ArrayList<>();
        stringList.add("cx");
        stringList.add("cxx");
        stringList.add("cxxx");
        //public static void checkArgument(boolean expression);
        //public static void checkArgument(boolean expression, @Nullable Object errorMessage);
        //public static void checkArgument(boolean expression,@Nullable String errorMessageTemplate,@Nullable Object... errorMessageArgs)
        //复制代码
        int i=-1;
        //判断表达式的结果 为false 抛出异常IllegalArgumentException
        Preconditions.checkArgument(i >= 0);
        //判断表达式的结果 为false 输出后面指定的errorMessage
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
        // 结果如下:
        // java.lang.IllegalArgumentException: Argument was -1 but expected nonnegative


        //checkNotNull 入参是个任意类型的对象, 函数校验这个对象是否为null,
        // 如果为空, 抛出NullPointerException, 否则直接返回该对象,
        // 所以checkNotNull的用法就比较有趣, 可以在调用setter方法前作前置校验. 例子如下:
        Preconditions.checkNotNull(i>= 0);

        //checkElementIndex 这个函数用于判断指定数组, 列表, 字符串的下标是否越界,?index是下标,?size是数组, 列表或字符串的长度,
        // 下标的有效范围是[0,数组长度)?即?0<=index<size.
        // 如果数组下标越界(即index<0 或者?index>=size),
        // 那么抛出IndexOutOfBoundsException异常, 否则返回数组的下标, 也就是index. 例子如下:
        Preconditions.checkElementIndex(3,stringList.size());
        //checkPositionIndex
        // checkElementIndex函数的下标有效范围是[0, 数组长度),
        // 而checkPositionIndex函数的下标有有效范围是[0, 数组长度], 即0<=index<=size. 例子如下:
        Preconditions.checkPositionIndex(3,stringList.size());
        //checkPositionIndexes 这个函数是用于判断[start,end]这个范围是否是个有效范围,
        // 即[start, end]?是否在[0, size]?范围内(如果[start, end]?和[0, size]相同, 也认为在范围内),
        // 如果不在, 则抛出IndexOutOfBoundsException异常. 例子如下:
        Preconditions.checkPositionIndexes(2,3,4);
    }
}
