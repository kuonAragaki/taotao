package com.shopping.item.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by baiqiang on 2017/10/16.
 */
public class FreemarkerTest
{
//    @Test
//    public void testFreemarkerFirst() throws Exception {
//        // 创建一个Configuration对象
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        // 设置模板所在的目录
//        configuration.setDirectoryForTemplateLoading(new File("D:\\Workspaces\\ShoppingWorkspace\\shopping-item-web\\src\\main\\resources\\ftl"));
//        // 设置模板字符集
//        configuration.setDefaultEncoding("UTF-8");
//        // 加载模板文件
//        Template template = configuration.getTemplate("first.htm");
//        // 创建一个数据集
//        Map data = new HashMap();
////        data.put("hello", "Hello Freemarker!!!");
//        data.put("title", "Hello Freemarker!!!");
//        data.put("stu", new Student(1, "小明", 18, "温都水城"));
//        // 设置模板输出的目录及输出的文件名
////        FileWriter writer = new FileWriter(new File("E:/temp/freemarker/hello.html"));
//        FileWriter writer = new FileWriter(new File("E:/temp/freemarker/first.html"));
//        // 生成文件
//        template.process(data, writer);
//        // 关闭流
//        writer.close();
//    }
//
//    @Test
//    public void testFreemarkerFirstHtm() throws Exception {
//        // 创建一个Configuration对象
//        Configuration configuration = new Configuration(Configuration.getVersion());
//        // 设置模板所在的目录
//        configuration.setDirectoryForTemplateLoading(new File("D:\\Workspaces\\ShoppingWorkspace\\shopping-item-web\\src\\main\\resources\\ftl"));
//        // 设置模板字符集
//        configuration.setDefaultEncoding("UTF-8");
//        // 加载模板文件
//        // Template template = configuration.getTemplate("hello.ftl");
//        Template template = configuration.getTemplate("second.htm");
//        // 创建一个数据集
//        Map data = new HashMap();
//        data.put("hello", "Hello Freemarker!!!");
//        data.put("title", "Hello Freemarker!!!");
//        data.put("stu", new Student(1, "小明", 18, "温都水城"));
//
//        List<Student> stuList = new ArrayList<Student>();
//        stuList.add(new Student(1, "小明1", 18, "温都水城"));
//        stuList.add(new Student(2, "小明2", 17, "温都水城"));
//        stuList.add(new Student(3, "小明3", 16, "温都水城"));
//        stuList.add(new Student(4, "小明4", 15, "温都水城"));
//        stuList.add(new Student(5, "小明5", 14, "温都水城"));
//        stuList.add(new Student(6, "小明6", 13, "温都水城"));
//        stuList.add(new Student(7, "小明7", 12, "温都水城"));
//        data.put("stuList", stuList);
//        data.put("date", new Date());
//        data.put("mytest", null);
//        // 设置模板输出的目录及输出的文件名
//        // FileWriter writer = new FileWriter(new File("F:/temp/freemarker/hello.html"));
//        FileWriter writer = new FileWriter(new File("E:/temp/freemarker/second.html"));
//        // 生成文件
//        template.process(data, writer);
//        // 关闭流
//        writer.close();
//    }
}