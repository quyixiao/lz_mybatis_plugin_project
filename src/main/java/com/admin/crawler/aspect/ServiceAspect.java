package com.admin.crawler.aspect;

import com.admin.crawler.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.catalina.connector.ResponseFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ServiceAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private int i;                              //【这样设置变量是有问题的，会存在并发问题】

    @Pointcut(value = "execution(* com..service..*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try {
            logger.info("service 方法开始执行");
            result = point.proceed();
            logger.info("service 方法执行结束：" + result);
            return result;
        } catch (Exception e) {
            result = R.error(e.getMessage());
            logger.error("controller error.", e);
        } finally {

        }
        return result;
    }

}
