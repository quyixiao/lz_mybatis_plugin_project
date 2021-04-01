
package com.admin.crawler.utils;

import java.text.SimpleDateFormat;

public class OrderUtil {

    public static String getUserPoolOrder(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(pre);
        return sb.append(dateformat.format(System.currentTimeMillis()))
                .append((int) (Math.random() * 1000)).toString();
    }
}
