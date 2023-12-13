package com.example.dailytest.demos.Test;

import com.example.dailytest.demos.Entity.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.util.Assert;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/12 10:41
 */
public class TestApacheCommons {
    public static void main(String[] args) throws Exception {
        // 如何把list集合拼接成以逗号分隔的字符串 a,b,c
        List<String> list = Arrays.asList("a", "b", "c");
        // 第一种方法，可以用stream流
        String join = list.stream().collect(Collectors.joining(","));
        System.out.println(join); // 输出 a,b,c
        // 第二种方法，其实String也有join方法可以实现这个功能
        String join2 = String.join(",", list);
        System.out.println(join); // 输出 a,b,c

        //比较两个字符串是否相等，忽略大小写
        String strA ="cx";
        String strB ="CX";
        if (strA.equalsIgnoreCase(strB)) {
            System.out.println("相等");
        }
        //比较两个对象是否相等
        Objects.equals(strA, strB);
        //两个List集合取交集
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");
        list1.retainAll(list2);
        System.out.println(list1); // 输出[a, b]

        /*
        commons-lang3
            maven 依赖：
                <dependency>
                    <groupId>org.apache.commons</groupId>
                    <artifactId>commons-lang3</artifactId>
                    <version>3.12.0</version>
                </dependency>
         */
        // 判空的时候，会去除字符串中的空白字符，比如空格、换行、制表符 isBlank isNotBlank
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isBlank("    "));
        // 为null 或者长度为0 isEmpty isNotEmpty
        System.out.println(StringUtils.isEmpty(" "));
        System.out.println(StringUtils.isEmpty("    "));
        //首字母转成大写
        String str = "yideng";
        String capitalize = StringUtils.capitalize(str);
        System.out.println(capitalize); // 输出Yideng
        //重复拼接字符串
        String str2 = StringUtils.repeat("ab", 2);
        System.out.println(str2); // 输出abab
        //格式化日期
        // Date类型转String类型
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(date); // 输出 2021-05-01 01:01:01
        // String类型转Date类型
        Date date1 = DateUtils.parseDate("2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss");
        // 计算一个小时后的日期
        Date date2 = DateUtils.addHours(new Date(), 1);
        /**
         * 包装临时对象
         * 当一个方法需要返回两个及以上字段时，我们一般会封装成一个临时对象返回，现在有了Pair和Triple就不需要了
         */
        // 返回两个字段
        ImmutablePair<Integer, String> pair = ImmutablePair.of(1, "yideng");
        System.out.println(pair.getLeft() + "," + pair.getRight()); // 输出 1,yideng
        // 返回三个字段
        ImmutableTriple<Integer, String, Date> triple = ImmutableTriple.of(1, "yideng", new Date());
        System.out.println(triple.getLeft() + "," + triple.getMiddle() + "," + triple.getRight()); // 输出 1,yideng,Wed Apr 07 23:30:00 CST 2021

        /*
        commons-collections
            maven 依赖：
                <dependency>
                    <groupId>org.apache.commons</groupId>
                    <artifactId>commons-collections4</artifactId>
                    <version>4.4</version>
                </dependency>
         common-beanutils
            maven 依赖：
            <dependency>
                <groupId>commons-beanutils</groupId>
                <artifactId>commons-beanutils</artifactId>
                <version>1.9.4</version>
            </dependency>
         */
        List<String> listA=new ArrayList<>();
        List<String> listB=new ArrayList<>();
        // 两个集合取交集
        Collection<String> collection = CollectionUtils.retainAll(listA, listB);
        // 两个集合取并集
        Collection<String> collection2 = CollectionUtils.union(listA, listB);
        // 两个集合取差集
        Collection<String> collection3 = CollectionUtils.subtract(listA, listB);

        User user = new User();
        BeanUtils.setProperty(user, "id", 1);
        BeanUtils.setProperty(user, "name", "yideng");
        System.out.println(BeanUtils.getProperty(user, "name")); // 输出 yideng
        System.out.println(user); // 输出 {"id":1,"name":"yideng"}

        // 对象转map
        Map<String, String> map = BeanUtils.describe(user);
        System.out.println(map); // 输出 {"id":"1","name":"yideng"}
        // map转对象
        User newUser = new User();
        BeanUtils.populate(newUser, map);
        System.out.println(newUser); // 输出 {"id":1,"name":"yideng"}

        /*
        commons-io
            maven 依赖：
            <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>2.8.0</version>
            </dependency>
         */
        File file = new File("demo1.txt");
        File srcFile = new File("srcFile.txt");
        File destFile = new File("destFile.txt");
        // 读取文件
        List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
        // 写入文件
        FileUtils.writeLines(new File("demo2.txt"), lines);
        // 复制文件
        FileUtils.copyFile(srcFile, destFile);
//        Assert.notEmpty();
    }

}
