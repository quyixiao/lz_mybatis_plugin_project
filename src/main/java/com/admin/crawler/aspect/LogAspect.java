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
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final ThreadLocal<String> threadLocalNo = new ThreadLocal();
    public static final ThreadLocal<String> myThreadLocalNo = new ThreadLocal();
    public static final ThreadLocal<Long> threadLocalTime = new ThreadLocal();
    public static final TransmittableThreadLocal<Long> inheritableThreadLocalTime = new TransmittableThreadLocal();
    public static final TransmittableThreadLocal<String> inheritableThreadLocalNo = new TransmittableThreadLocal();

    /***
     *  public static void main(String[] args)throws Exception {
     *
     *         for (int i = 0; i < 10; i++) {
     *             a(i);
     *         }
     *     }
     *
     *     public static void a(final int i ){
     *         new Thread(new Runnable() {
     *             @Override
     *             public void run() {
     *                 try {
     *                     HttpUtils.get("http://localhost:8080/testThreadTest?i=" + i);
     *                 } catch (IOException e) {
     *                     e.printStackTrace();
     *                 }
     *             }
     *         }).start();
     *     }
     */
    private int i;                              //???????????????????????????????????????????????????????????????

    @Pointcut(value = "execution(* com..controller..*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String logNo = OrderUtil.getUserPoolOrder("tr");
        long start = System.currentTimeMillis();
        threadLocalNo.set(logNo);
        inheritableThreadLocalNo.set(logNo);
        inheritableThreadLocalTime.set(start);
        Object result = null;
        String uri = "";
        StringBuilder cm = new StringBuilder();
        result = null;
        Object arg = result;
        String ip = "";
        String m = "";
        String userName = "";
        String params = "";

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Object[] args = point.getArgs();
            List<Object> argxx= new ArrayList<>();
            //?????????spring?????????http???????????????????????????jsonstring????????????
            if (args != null && args.length > 0) {
                for (Object arg1 : args) {
                    if (arg instanceof HttpServletResponse) {
                        continue;
                    } else if (arg1 instanceof HttpServletRequest) {
                        continue;
                    } else if (arg1 instanceof MultipartFile) {
                        continue;
                    } else if (arg1 instanceof MultipartFile[]) {
                        continue;
                    } else if (arg1 instanceof ResponseFacade) {
                        continue;
                    } else {
                        argxx.add(arg1);
                    }
                }
            }
            m = request.getMethod();
            uri = request.getRequestURI();
            ip = ServletUtils.getIpAddress(request);

            // result???????????????????????????????????????
            String classMethod = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
            if (StringUtil.isNotBlank(classMethod) && classMethod.length() > 0 && classMethod.contains(".")) {
                String classMethods[] = classMethod.split("\\.");
                if (classMethod.length() >= 2) {
                    cm.append(classMethods[classMethods.length - 2]).append(".").append(classMethods[classMethods.length - 1]);
                }
            }
            params = JSON.toJSONString(argxx);
            //int b = Integer.parseInt(arg.toString());
            //i = b;
            //logger.info("????????????i ============================ " + i);
            result = point.proceed();
            //logger.info("????????????i ++++++++++++++++++++++++++++++????????? " +b +"??????????????? " + i);
            return result;
        } catch (Exception e) {
            result = R.error(e.getMessage());
            logger.error("controller error.", e);
            // ?????????????????????????????????
            // PDingDingUtils.sendText("?????? " +  ch.qos.logback.classic.Logger.inheritableThreadLocalNo.get() + "\n"+ ExceptionUtils.dealException(e));
        } finally {
            logger.info(StringUtil.appendStrs(
                    "	", "cm=", cm.toString(),
                    "	", "m=", m,
                    "	", "uri=", uri,
                    "	", "userName=", userName,
                    "	", "ip=", ip,
                    "   ", "params=", params,
                    "   ", "result=", JSON.toJSONString(result)
            ));
            threadLocalNo.remove();
            inheritableThreadLocalNo.remove();
            inheritableThreadLocalTime.remove();
        }
        return result;
    }

}
