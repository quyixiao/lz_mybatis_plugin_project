package com.admin.crawler.service.impl;

import com.admin.crawler.service.HelloRMIService;
import org.springframework.stereotype.Service;


@Service("rmiService")
public class HelloRMIServiceImpl implements HelloRMIService {
    @Override
    public int getAdd(int a, int b) {
        System.out.println(" a = "+ a + " , b = "+ b + ",result = "+ (a + b ));
        return a + b ;
    }
}
