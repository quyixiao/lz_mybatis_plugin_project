package com.admin.crawler.controller;

import com.admin.crawler.service.impl.HttpInvokerTestImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
public class HttpInvokerServerController {

    @RequestMapping("/httpinvokertest.service")
    public String test(String methodName,Class<?> [] paramterTypes,Object [] args) throws Exception{
        Method method = HttpInvokerTestImpl.class.getDeclaredMethod(methodName,paramterTypes);
        return method.invoke(args) + "";
    }

}
