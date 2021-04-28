package com.admin.crawler.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class RequestWrapperFilter implements Filter {

    @Override
	public void init(FilterConfig config) {
        log.info("==>RequestWrapperFilter启动");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws java.io.IOException, ServletException {
        try {
            MultiReadHttpServletRequestWrapper requestWrapper = new MultiReadHttpServletRequestWrapper((HttpServletRequest) request);

            chain.doFilter(requestWrapper, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}