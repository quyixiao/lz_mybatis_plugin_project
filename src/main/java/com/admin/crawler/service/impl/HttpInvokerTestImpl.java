package com.admin.crawler.service.impl;

import com.admin.crawler.service.HttpInvokerTestI;

public class HttpInvokerTestImpl implements HttpInvokerTestI {
    @Override
    public String getTestPo(String desp) {

        return "getTestPo " + desp;
    }
}