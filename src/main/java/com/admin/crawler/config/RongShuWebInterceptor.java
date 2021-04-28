package com.admin.crawler.config;

import com.admin.crawler.entity.RongShuReqBo;
import com.admin.crawler.utils.BairongSignature;
import com.admin.crawler.utils.OrderUtil;
import com.admin.crawler.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RongShuWebInterceptor extends HandlerInterceptorAdapter {

    public static final String rongshu_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHv2Gg4rgRIy89msbjrgtEA4h27Qgq+x1p17bZ" +
            "DLLIgioiXbvPQUVLNeumQZS7t6v393yJYLg7hPUb/Nsu39cEkGLmSGxJ81eV8/QZF/5YuLG6piSg" +
            "0YjjUYDxb+j4y4gxMeDhmgCUVLpOn1HCQH5rbYLlxQGo1sqD3sxyRACyvQIDAQAB";

    public static final String rongshu_private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIe/YaDiuBEjLz2axuOuC0QDiHbt" +
            "CCr7HWnXttkMssiCKiJdu89BRUs166ZBlLu3q/f3fIlguDuE9Rv82y7f1wSQYuZIbEnzV5Xz9BkX" +
            "/li4sbqmJKDRiONRgPFv6PjLiDEx4OGaAJRUuk6fUcJAfmttguXFAajWyoPezHJEALK9AgMBAAEC" +
            "gYBnTGRveHdXWy3ktDYrJdMe041x6lfv1R2B4IwcyQP3fs2vTyLOyT2AySxkXL1gx5kOXf8fz0EC" +
            "0d9vh9AG8ID+s735CmnGo01HNKBKT+OqjX9azHvlo7+9tzU5FPUbqCEVgDZ3DxAxeLthx6I6O14Y" +
            "sQGXls/tr99RtE18B+o/4QJBAM+rQR3AHGDW69+5ZAyATYh+jK5aeqjrJtnAoEdtS7UX6vKiwiwX" +
            "1k27/bMhvcYM36VIfXVvquv1oz78l1PJfikCQQCnVxxKublxR9om/8Hh2LHMEPMXJ8Nte1dRRdN5" +
            "kR1R1i/EXP4h5RBr4LhnUZL+n3UbquZCDUJO/Ho6bz2gxvp1AkBzZhTfNVwPrB6SyfCAyeNL0BbY" +
            "5Ep5B4MlSddHGcMSNOJIRo1j9zMNK0QFgOB15713mVquIYl3aZ53Z+Gnm7ZRAkAT42GEbBBjK0P9" +
            "MJZtbiCrfTQ3A03RvEADc8YPg7S1XjxtHRRDGQNbnuirtuE+i9sSP7yACy1fT9iDC2eKrTtlAkAu" +
            "FreIRrJOxE6QFWZvaH/2wgQj4isyJ/lDzGZEFzEuH2KKpjudYqgkq5vPWMVTNZBnrM6i5k3Yxf6A" +
            "M0DpfAFg";

    public static final String rongshu_aes = "01A7210BF01F2824235BC2971DFE1D47";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String orderNo = OrderUtil.getUserPoolOrder("filter");
        if (request instanceof MultiReadHttpServletRequestWrapper) {
            MultiReadHttpServletRequestWrapper requestWrapper = (MultiReadHttpServletRequestWrapper) request;
            String mediaType = request.getContentType();
            if (StringUtil.isNotBlank(mediaType) && mediaType.toLowerCase().contains("json")) {
                String body = requestWrapper.getBody();
                log.info(orderNo + " 榕树请求参数：" + body);
                if (StringUtil.isNotBlank(body)) {
                    RongShuReqBo rongShuReqBo = JSON.parseObject(body, RongShuReqBo.class);
                    Map<String, String> signParams = new HashMap<>();
                    signParams.put("channelId", rongShuReqBo.getChannelId());
                    signParams.put("timestamp", rongShuReqBo.getTimestamp());
                    signParams.put("request", rongShuReqBo.getRequest());
                    boolean flag = BairongSignature.checkSignSHA256(signParams, rongShuReqBo.getSign(), rongshu_public_key);
                    if (flag) {
                        String requestStr = rongShuReqBo.getRequest();
                        String temp = BairongSignature.decryptAES(rongshu_aes, requestStr);
                        Map map = JSON.parseObject(temp, Map.class);
                        ((MultiReadHttpServletRequestWrapper) request).setBody(JSON.toJSONString(map));
                        return true;
                    } else {
                        log.info(orderNo + " 验签名失败");
                    }
                }
            } else {
                log.info(orderNo + " 榕树请求不是json请求");
            }
        } else {
            log.info(orderNo + " request 请求没有实现 MultiReadHttpServletRequestWrapper ");
        }
        return false;
    }

}