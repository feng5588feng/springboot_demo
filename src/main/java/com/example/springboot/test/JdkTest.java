package com.example.springboot.test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.entity.Student;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JdkTest {

    public static void main(String[] args) {

        System.out.println(StringUtils.countOccurrencesOf("abcabcabc", "abc"));

        //Consumer<String> consumer = t -> System.out.println(t.toUpperCase());
        //Consumer<String> consumer = (var t) -> System.out.println(t.toUpperCase());
        //System.out.println(consumer);

        /*String str = " woshidage ";
        boolean isblank = str.isBlank();  //判断字符串是空白
        boolean isempty = str.isEmpty();  //判断字符串是否为空
        String  result1 = str.strip();    //首位空白
        String  result2 = str.stripTrailing();  //去除尾部空白
        String  result3 = str.stripLeading();  //去除首部空白
        String  copyStr = str.repeat(2);  //复制几遍字符串
        long  lineCount = str.lines().count();  //行数统计

        System.out.println(isblank);
        System.out.println(isempty);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(copyStr);
        System.out.println(lineCount);*/

        /*List<Student> list = Arrays.asList(
                new Student("九天", "男", 5000, 18, "天秤座"),
                new Student("十夜", "男", 4000, 16, "双鱼座"),
                new Student("十一郎", "男", 3000, 24, "水瓶座")
        );
        list.stream().dropWhile(x -> x.getAge() >= 18).forEach(System.out::println);
        Stream.iterate(1, i -> i < 5, i -> i + 1).forEach(System.out::println);*/

        /*var list = new ArrayList<>();

        String aa = "aaa";

        list.add("first");
        list.add("second");
        list.add("third");
        System.out.println(JSON.toJSONString(list).getBytes().length);

        var result = List.copyOf(list);

        System.out.println(result.toString());

        list.addAll(result);
        System.out.println(list.toString());

        System.out.println(JSON.toJSONString(list).getBytes().length);*/


        String str = "我喜欢java";
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int c = 0;
        while((c = bis.read()) != -1) {
            bos.write(c);
        }
        //bos.toString() 默认的使用的UTF-8编码
        System.out.println(bos.toString());


    }

}
