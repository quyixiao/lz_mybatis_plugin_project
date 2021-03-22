package com.test;

import com.lz.mybatis.plugin.utils.SqlParseUtils;
import org.apache.ibatis.reflection.ParamNameUtil;

import java.lang.reflect.Method;

public class DiscoverMethodParamAnnotation {

    public static void main(String[] args) {
        Method method = SqlParseUtils.getMethod(Test.class, "a");
        String paramName = ParamNameUtil.getParamNames(method).get(0);
        System.out.println(paramName);
    }
}
