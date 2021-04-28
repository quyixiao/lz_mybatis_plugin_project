package com.admin.crawler.utils;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoqing on 2018/4/17.
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static CloseableHttpClient client = null;

    static {
        client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(RequestConfig
                        .custom()
                        .setConnectionRequestTimeout(3000)
                        .setConnectTimeout(10000)
                        .setSocketTimeout(20000)
                        .build())
                .setMaxConnTotal(1200)
                .setMaxConnPerRoute(300)
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .build();
    }

    public static void init(CloseableHttpClient newClient) {
        client = newClient;
    }

    public static Pair<Integer, String> postByJson(String url, String paramJson, Map<String, String> headers) {
        String content = "";
        CloseableHttpResponse response = null;
        int statusCode = -1;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Connection", "close");
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (paramJson != null && !paramJson.isEmpty()) {
                StringEntity entity = new StringEntity(paramJson, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = client.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.error("Http client request failed, response code " + statusCode);
            }
            InputStream inputStream = response.getEntity().getContent();
            content = CharStreams.toString(new InputStreamReader(inputStream, Consts.UTF_8));
            inputStream.close();
        } catch (Exception e) {
            logger.error("Http client request failed", e);
        } finally {
            try {
                if (response != null) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        EntityUtils.consume(entity);
                    }
                    response.close();
                }
            } catch (IOException e) {
            }
        }
        return Pair.of(statusCode, content);
    }

    public static Pair<Integer, String> postByJson(String url, String paramJson) {
        return postByJson(url, paramJson, null);
    }

    public static Pair<Integer, byte[]> postByJsonToBytes(String url, String paramJson, Map<String, String> headers) {
        CloseableHttpResponse response = null;
        int statusCode = -1;
        byte[] result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Connection", "close");
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (paramJson != null && !paramJson.isEmpty()) {
                StringEntity entity = new StringEntity(paramJson, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = client.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
            InputStream inputStream = response.getEntity().getContent();
            if (statusCode != 200) {
                String content = CharStreams.toString(new InputStreamReader(inputStream, Consts.UTF_8));
                logger.error("Http client request failed, response code {}, result {}.", statusCode, content);
            } else {
                result = ByteStreams.toByteArray(inputStream);
            }
            inputStream.close();
        } catch (Exception e) {
            logger.error("Http client request failed", e);
        } finally {
            try {
                if (response != null) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        EntityUtils.consume(entity);
                    }
                    response.close();
                }
            } catch (IOException e) {
            }
        }
        return Pair.of(statusCode, result);
    }

    public static Pair<Integer, byte[]> postByJsonToBytes(String url, String paramJson) {
        return postByJsonToBytes(url, paramJson, null);
    }

}
