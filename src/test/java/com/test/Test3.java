package com.test;

import com.admin.crawler.config.RongShuWebInterceptor;
import com.admin.crawler.utils.BairongSignature;
import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.util.RepositoryIdCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test3 {

    public static void main(String[] args) {


        Map param = new HashMap();
        param.put("channelId", "rongshu");
        param.put("timestamp", "1533710259153");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("loanAmount","15000");
        requestMap.put("rate","0.3000");
        requestMap.put("repayMethod","0");
        requestMap.put("loanPeriod","12");
        requestMap.put("periodUnit","2");
        String request = BairongSignature.encryptAES(RongShuWebInterceptor.rongshu_aes, JSON.toJSONString(requestMap));
        param.put("request",request);
        //生成签名字符串
        System.out.println("sign param :" + BairongSignature.getSignContent(param));
        String signStr = BairongSignature.signSHA256(param, RongShuWebInterceptor.rongshu_private_key);
        System.out.println("sign str :" + signStr);
        param.put("sign", signStr);
        System.out.println(JSON.toJSONString(param));







    }
}
