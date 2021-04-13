package com.admin.crawler.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.admin.crawler.aspect.LogAspect;
import com.admin.crawler.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogPreConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        if (LogAspect.inheritableThreadLocalNo != null && LogAspect.inheritableThreadLocalNo.get() != null) {
            StringBuffer sb = new StringBuffer();
            String threadNo = LogAspect.threadLocalNo.get();
            if (threadNo == null || threadNo.length() == 0) {
                if(LogAspect.myThreadLocalNo.get() ==null  ){
                    LogAspect.myThreadLocalNo.set(OrderUtil.getUserPoolOrder("cr"));
                }
                threadNo = LogAspect.myThreadLocalNo.get();
            } else {
                LogAspect.myThreadLocalNo.remove();
            }
            sb.append("[").append(LogAspect.inheritableThreadLocalNo.get()).append(",").append(threadNo).append("]").append("\t");
            Long start = LogAspect.inheritableThreadLocalTime.get();
            Long end;
            if (start != null) {
                end = System.currentTimeMillis();
                sb.append("exet=").append(end - start).append("\t");
            }
            return sb.toString();
        } else {
            return "";
        }
    }







    //保留之前的实现
    public String convert_back(ILoggingEvent event) {
        if (LogAspect.threadLocalNo != null && LogAspect.threadLocalNo.get() != null) {
            StringBuffer sb = new StringBuffer(LogAspect.threadLocalNo.get());
            sb.append("\t");
            Long start = LogAspect.threadLocalTime.get();
            Long end;
            if (start != null) {
                end = System.currentTimeMillis();
                sb.append("exet=").append(end - start).append("\t");
            }
            return sb.toString();
        } else {
            return "";
        }
    }

} 