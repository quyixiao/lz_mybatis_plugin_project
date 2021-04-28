package com.admin.crawler.config;

import com.admin.crawler.utils.AbstractRequestUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Slf4j
public class MultiReadHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private String tempBody;
	private Map params;

	public MultiReadHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		tempBody = AbstractRequestUtils.getRequestBody(request);
		params = new HashMap(request.getParameterMap());
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tempBody.getBytes());
		return new ServletInputStream() {
			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public int readLine(byte[] b, int off, int len) throws IOException {
				return super.readLine(b, off, len);
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}

			@Override
			public int read() {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public String getBody() {
		return this.tempBody;
	}

	public void setBody(String body) {
		this.tempBody = body;
	}

	@Override
	public Map getParameterMap() {
		return params;
	}

	@Override
	public Enumeration getParameterNames() {
		Vector l = new Vector(params.keySet());
		return l.elements();
	}


	@Override
	public String[] getParameterValues(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			return (String[]) v;
		} else if (v instanceof String) {
			return new String[]{(String) v};
		} else {
			return new String[]{v.toString()};
		}
	}


	@Override
	public String getParameter(String name) {
		Object v = params.get(name);
		if (v == null) {
			return null;
		} else if (v instanceof String[]) {
			String[] strArr = (String[]) v;
			if (strArr.length > 0) {
				return strArr[0];
			} else {
				return null;
			}
		} else if (v instanceof String) {
			return (String) v;
		} else {
			return v.toString();
		}
	}

	public void setParams(Map params) {
		this.params = params;
	}
}