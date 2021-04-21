package com.admin.crawler.config;

import com.admin.crawler.service.HelloRMIService;
import com.admin.crawler.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class RMIServiceConfig {


    @Bean
    public RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(SpringContextUtils.getApplicationContext().getBean("rmiService"));
        rmiServiceExporter.setServiceName("helloRMI");
        rmiServiceExporter.setServiceInterface(HelloRMIService.class);
        rmiServiceExporter.setRegistryPort(9999);
        return rmiServiceExporter;
    }

/*
    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://127.0.0.1:9999/helloRMI");
        factoryBean.setServiceInterface(HelloRMIService.class);
        return factoryBean;
    }*/

}
