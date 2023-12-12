package com.example.dailytest.demos.Test;

import com.example.dailytest.demos.BloomFliter.MyBloomFilter;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/11 15:06
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(3 << 2);
        System.out.println(-2 >>> 1);

        MyBloomFilter myBloomFilter=new MyBloomFilter();
        myBloomFilter.add("cx");

        System.out.println(myBloomFilter.contains("cx"));
        System.out.println(myBloomFilter.contains("cxx"));
    }
}
