package com.test;

import com.admin.crawler.mapper.TestUserMapper;
import com.lz.mybatis.plugin.utils.SqlParseUtils;
import com.lz.mybatis.plugin.utils.t.Tuple2;
import com.lz.mybatis.plugin.utils.t.Tuple5;

public class SqlTest2 {

    public static void main(String[] args) {
        Tuple5<Boolean, String,String,String,String> pluginTuple1 = SqlParseUtils.testSelect(TestUserMapper.class, "selectPage").getData();

        Tuple5<Boolean, String,String,String,String> pluginTuple2 = SqlParseUtils.testUpdate(TestUserMapper.class, "updateTestUserById").getData();
        Tuple5<Boolean, String,String,String,String> pluginTuple3 = SqlParseUtils.testUpdate(TestUserMapper.class, "updateBatchTestUser").getData();
        Tuple5<Boolean, String,String,String,String> pluginTuple4 = SqlParseUtils.testCount(TestUserMapper.class, "countUser").getData();
        Tuple5<Boolean, String,String,String,String> pluginTuple5 = SqlParseUtils.testDelete(TestUserMapper.class, "deleteTestUserById").getData();
        Tuple5<Boolean, String,String,String,String> pluginTuple6 = SqlParseUtils.testInsert(TestUserMapper.class, "insertTestUser").getData();

        System.out.println("元组第一个参数：" + pluginTuple1.getFirst());            //是否强制使用@Param 注解
        System.out.println("元组第二个参数：" +pluginTuple1.getSecond());            //根据方法及注解生成的 SQL
        System.out.println("元组第三个参数：" +pluginTuple1.getThird());             //返回需要回显的列
        System.out.println("元组第四个参数：" +pluginTuple1.getFourth());            //需要增加到Configuration 中的 resultMapId
        System.out.println("元组第五个参数：" +pluginTuple1.getFifth());             //Mapper.xml中 <resultMap .../>



        System.out.println("元组第一个参数：" + pluginTuple6.getFirst());            //是否强制使用@Param 注解
        System.out.println("元组第二个参数：" +pluginTuple6.getSecond());            //根据方法及注解生成的 SQL
        System.out.println("元组第三个参数：" +pluginTuple6.getThird());             //返回需要回显的列
        System.out.println("元组第四个参数：" +pluginTuple6.getFourth());            //需要增加到Configuration 中的 resultMapId
        System.out.println("元组第五个参数：" +pluginTuple6.getFifth());             //Mapper.xml中 <resultMap .../
    }



}
