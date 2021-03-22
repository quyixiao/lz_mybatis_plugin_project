package com.test;

import com.admin.crawler.mapper.TestUserMapper;
import com.lz.mybatis.plugin.utils.SqlParseUtils;
import com.lz.mybatis.plugin.utils.t.Tuple2;
import com.lz.mybatis.plugin.utils.t.Tuple5;

public class SqlTest {

    public static void main(String[] args) {
        Tuple5<Boolean, String,String,String,String> pluginTuple = SqlParseUtils.testSelect(TestUserMapper.class, "selectPage").getData();
        //Tuple2<Boolean, String> pluginTuple = SqlParseUtils.testUpdate(TestUserMapper.class, "updateTestUserById").getData();
        System.out.println("元组第一个参数：" + pluginTuple.getFirst());            //是否强制使用@Param 注解
        System.out.println("元组第二个参数：" +pluginTuple.getSecond());            //根据方法及注解生成的 SQL
        System.out.println("元组第三个参数：" +pluginTuple.getThird());             //返回需要回显的列
        System.out.println("元组第四个参数：" +pluginTuple.getFourth());            //需要增加到Configuration 中的 resultMapId
        System.out.println("元组第五个参数：" +pluginTuple.getFifth());             //Mapper.xml中 <resultMap .../>
    }



}
