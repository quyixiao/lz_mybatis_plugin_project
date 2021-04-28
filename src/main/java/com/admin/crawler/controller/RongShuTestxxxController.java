package com.admin.crawler.controller;

import com.admin.crawler.config.MultiReadHttpServletRequestWrapper;
import com.admin.crawler.utils.R;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.AsyncWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rstest")
public class RongShuTestxxxController {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;


    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @RequestMapping("/**")
    public R getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object methodObj = getLookupHandlerMethodMethod(handlerMapping);
        if (methodObj != null) {
            HandlerMethod handlerMethod = (HandlerMethod) (methodObj);
        }
        System.out.println("9ids9ds98");
        Map<String, Object> map = new HashMap<>();
        map.put("username", new String[]{"zhangsan"});
        map.put("phone",new String[]{ "18458195149"});

        HandlerMethod handlerMethod = getLookupHandlerMethodMethod(handlerMapping);
        if (handlerMethod != null) {
            MultiReadHttpServletRequestWrapper requestWrapper = new MultiReadHttpServletRequestWrapper((HttpServletRequest) request);
            requestWrapper.setParams(map);

            requestWrapper.setBody(JSON.toJSONString(map));

            ServletWebRequest webRequest = new ServletWebRequest(requestWrapper, response);
            WebDataBinderFactory binderFactory = invokeMethod(RequestMappingHandlerAdapter.class, "getDataBinderFactory",
                    adapter, new Object[]{handlerMethod}, HandlerMethod.class);// adapter.getDataBinderFactory(handlerMethod);
            ModelFactory modelFactory = invokeMethod(RequestMappingHandlerAdapter.class, "getModelFactory",
                    adapter, new Object[]{handlerMethod, binderFactory}, HandlerMethod.class, WebDataBinderFactory.class);//adapter.getModelFactory(handlerMethod, binderFactory);
            ServletInvocableHandlerMethod invocableMethod = createInvocableHandlerMethod(handlerMethod);
            invocableMethod.setHandlerMethodArgumentResolvers(getFieldValue(adapter, "argumentResolvers"));
            invocableMethod.setHandlerMethodReturnValueHandlers(getFieldValue(adapter, "returnValueHandlers"));
            invocableMethod.setDataBinderFactory(binderFactory);
            invocableMethod.setParameterNameDiscoverer(getFieldValue(adapter, "parameterNameDiscoverer"));

            ModelAndViewContainer mavContainer = new ModelAndViewContainer();
            mavContainer.addAllAttributes(RequestContextUtils.getInputFlashMap(requestWrapper));
            modelFactory.initModel(webRequest, mavContainer, invocableMethod);
            mavContainer.setIgnoreDefaultModelOnRedirect(false);

            AsyncWebRequest asyncWebRequest = WebAsyncUtils.createAsyncWebRequest(requestWrapper, response);
            asyncWebRequest.setTimeout(getFieldValue(adapter, "asyncRequestTimeout"));

            WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(requestWrapper);
            asyncManager.setTaskExecutor(getFieldValue(adapter, "taskExecutor"));
            asyncManager.setAsyncWebRequest(asyncWebRequest);
            asyncManager.registerCallableInterceptors(getFieldValue(adapter, "callableInterceptors"));
            asyncManager.registerDeferredResultInterceptors(getFieldValue(adapter, "deferredResultInterceptors"));
            Object bean = invocableMethod.getBean();
            if (bean instanceof String) {
                BeanFactory beanFactory = getFieldValue(invocableMethod, "beanFactory");
                setFieldValue(invocableMethod, "bean", beanFactory.getBean(bean.toString()));
            }
            Object object = invocableMethod.invokeForRequest(webRequest, mavContainer);
            System.out.println(JSON.toJSONString(object));

        }
        return R.ok("成功");
    }

    public String getBody(HttpServletRequest request) throws IOException {
        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }

    protected ServletInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod) {
        return new ServletInvocableHandlerMethod(handlerMethod);
    }


    public static <T> T invokeMethod(Class clazz, String methodName, Object target, Object[] args, Class<?>... parameterTypes) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return (T) method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T setFieldValue(Object target, String name, Object value) {
        try {
            Field field = getField(target, name);
            if (field != null) {
                field.setAccessible(true);
                field.set(target, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T getFieldValue(Object target, String name) {
        try {
            Field field = getField(target, name);
            if (field != null) {
                field.setAccessible(true);
                return (T) field.get(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getField(Object target, String name) {
        Class currentClazz = target.getClass();
        Field field = doGetField(currentClazz, name);
        while (currentClazz != Object.class && field == null) {
            currentClazz = currentClazz.getSuperclass();
            field = doGetField(currentClazz, name);
        }
        return field;
    }

    public static Field doGetField(Class currentClazz, String name) {
        try {
            return currentClazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {

        }
        return null;
    }

    public static HandlerMethod getLookupHandlerMethodMethod(RequestMappingHandlerMapping handlerMapping) {
        try {
            Field field = AbstractHandlerMethodMapping.class.getDeclaredField("mappingRegistry");
            field.setAccessible(true);
            Object object = field.get(handlerMapping);
            Field mappingLookupField = object.getClass().getDeclaredField("mappingLookup");
            mappingLookupField.setAccessible(true);
            Object mappingLookupObj = mappingLookupField.get(object);
            Field urlLookupField = object.getClass().getDeclaredField("urlLookup");
            urlLookupField.setAccessible(true);
            Object urlLookupObj = urlLookupField.get(object);
            if (mappingLookupObj != null && urlLookupObj != null) {
                Map<RequestMappingInfo, HandlerMethod> mappingLookup = (Map<RequestMappingInfo, HandlerMethod>) mappingLookupObj;
                MultiValueMap<String, RequestMappingInfo> urlLookup = (MultiValueMap<String, RequestMappingInfo>) urlLookupObj;
                List<RequestMappingInfo> requestMappingInfos = urlLookup.get("/do/enter");
                RequestMappingInfo requestMappingInfo = requestMappingInfos.get(0);
                return mappingLookup.get(requestMappingInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
