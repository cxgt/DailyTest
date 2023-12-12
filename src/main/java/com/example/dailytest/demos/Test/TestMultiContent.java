package com.example.dailytest.demos.Test;

import com.alibaba.fastjson2.JSONObject;
import com.example.dailytest.demos.Entity.ArchiveNetDiscFile;
import com.google.common.collect.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/12 09:42
 */
public class TestMultiContent {
    public static void main(String[] args) {
        /**
         * MultiValueMap 通过这种方式在同一个key下插入不同的value
         */
        List<ArchiveNetDiscFile> allList=new ArrayList<>();
        fillList(allList);
        MultiValueMap<Long, Long> idMappingMap = new LinkedMultiValueMap<>();
        // 可以通过这种方式在同一个key下插入不同的value
        for (ArchiveNetDiscFile netDiscFile : allList) {
            // parentId有可能重复
            idMappingMap.add(netDiscFile.getParentId(), netDiscFile.getId());
        }
        // 然后下面可以这样拿出来, 用List<Long>接收
        List<Long> childIdList = idMappingMap.get(133l);
        System.out.println(JSONObject.toJSONString(childIdList));

        List<String> wordList=new ArrayList<>();
        wordList.add("the");
        wordList.add("the");
        wordList.add("hello");
        wordList.add("world");
        wordList.add("java");
         /*
            Multiset: 把重复的元素放入集合  Multiset 会是这样 : [car x 2, ship x 6, bike x 3]
          */
        HashMultiset<String> multiSet = HashMultiset.create();
        multiSet.addAll(wordList);
        System.out.println(JSONObject.toJSONString(multiSet));
        //count word “the”
        Integer count = multiSet.count("the");
        System.out.println(count);

        /*
            Multimap: 在 Map 的 value 里面放多个元素

            Muitimap 就是一个 key 对应多个 value 的数据结构。看上去它很像 java.util.Map 的结构，
            但是 Muitimap 不是 Map，没有实现 Map 的接口。设想你对 Map 调了 2 次参数 key 一样的 put 方法，
            结果就是第 2 次的 value 覆盖了第 1 次的 value。但是对 Muitimap 来说这个 key 同时对应了 2 个 value。
            所以 Map 看上去是 : {k1=v1, k2=v2,...}，而 Muitimap 是 :{k1=[v1, v2, v3], k2=[v7, v8],....}。

            Muitimap 接口的主要实现类有：
            HashMultimap: key 放在 HashMap，而 value 放在 HashSet，即一个 key 对应的 value 不可重复
            ArrayListMultimap: key 放在 HashMap，而 value 放在 ArrayList，即一个 key 对应的 value 有顺序可重复
            LinkedHashMultimap: key 放在 LinkedHashMap，而 value 放在 LinkedHashSet，即一个 key 对应的 value 有顺序不可重复
            TreeMultimap: key 放在 TreeMap，而 value 放在 TreeSet，即一个 key 对应的 value 有排列顺序
            ImmutableMultimap: 不可修改的 Multimap
         */
        HashMultimap<String, String> map = HashMultimap.create();
        map.put("cx","gss");
        map.put("cx","jcm");
        map.put("cx","gt");
        System.out.println(map.get("cx"));
        System.out.println(JSONObject.toJSONString(map));
        /*
            BiMap: 双向 Map
            BiMap 实现了 java.util.Map 接口。它的特点是它的 value 和它 key 一样也是不可重复的，
            换句话说它的 key 和 value 是等价的。如果你往 BiMap 的 value 里面放了重复的元素，
            就会得到 IllegalArgumentException。

            这里的 inverse 方法就是把 BiMap 的 key 集合 value 集合对调，
            因此 biMap == biMap.inverse().inverse()。

            BiMap的常用实现有：
            HashBiMap: key 集合与 value 集合都有 HashMap 实现
            EnumBiMap: key 与 value 都必须是 enum 类型
            ImmutableBiMap: 不可修改的 BiMap
         */
        BiMap biMap= HashBiMap.create();
        biMap.put("cx","cjc");
        biMap.put("jcm","wy");
        biMap.put("gss","xy");
        BiMap inverse = biMap.inverse();
        System.out.println(JSONObject.toJSONString(biMap));
        System.out.println(JSONObject.toJSONString(inverse));
        /**
         * Table 一种有两个key的HashMap
         */
        // 一批用户，同时按年龄和性别分组
        Table<Integer, String, String> table = HashBasedTable.create();
        table.put(18, "男", "yideng");
        table.put(18, "女", "Lily");
        System.out.println(table.get(18, "男")); // 输出 yideng
        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row(18);
        System.out.println(row); // 输出 {"男":"yideng","女":"Lily"}
        // 查看列数据
        Map<Integer, String> column = table.column("男");
        System.out.println(column); // 输出 {18:"yideng"}
    }

    private static void fillList(List<ArchiveNetDiscFile> allList) {
        ArchiveNetDiscFile.ArchiveNetDiscFileBuilder builder = ArchiveNetDiscFile.builder();
        ArchiveNetDiscFile build = builder.id(1l).parentId(133l).build();
        allList.add(build);
        ArchiveNetDiscFile.ArchiveNetDiscFileBuilder builder2 = ArchiveNetDiscFile.builder();
        ArchiveNetDiscFile build2 = builder.id(2l).parentId(133l).build();
        allList.add(build2);
        ArchiveNetDiscFile.ArchiveNetDiscFileBuilder builder3 = ArchiveNetDiscFile.builder();
        ArchiveNetDiscFile build3 = builder.id(3l).parentId(123l).build();
        allList.add(build3);
    }
}
