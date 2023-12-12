package com.example.dailytest.demos.BloomFliter;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/11 15:29
 */
public class GuavaBloomFilter {
    public static void main(String[] args) {
        // 创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        List<String> stringList=new ArrayList<>();
        stringList.add("cx");
        stringList.add("cxx");
        stringList.add("cxxx");
        String a="";
        String aBoolean = Preconditions.checkNotNull(a);
        Preconditions.checkArgument(stringList.contains("cx"));
        Preconditions.checkArgument(stringList.contains("ccx"));
        System.out.println(aBoolean);

    }
}
