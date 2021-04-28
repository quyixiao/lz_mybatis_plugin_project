package com.admin.crawler.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author wutao
 * @description
 * @date 2020-06-08
 */
@Slf4j
public class AbstractRequestUtils {
	public static String getRequestBody(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inputStream = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
//			int ch = 0;
//			while ((ch = reader.read(buffer)) != -1) {
//				fileout.write(buffer, 0, ch);
//			}
		} catch (IOException e) {
			log.error("参数转换错误",e);
		}
		return sb.toString();

	}
}
