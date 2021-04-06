package com.admin.crawler.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.admin.crawler.aspect.LogAspect;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogPreConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
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