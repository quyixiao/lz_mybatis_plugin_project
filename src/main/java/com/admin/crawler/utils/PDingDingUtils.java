package com.admin.crawler.utils;

import ch.qos.logback.classic.Logger;
import com.admin.crawler.entity.DingTalkDto;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class PDingDingUtils {
    public final static String access_token = "替换成你自己创建钉钉机器人的 access_token ";

    protected static String dingTalkUrlPre = "https://oapi.dingtalk.com/robot/send?access_token=" + access_token;

    public static void sendText( String text) {
        try {
            String content = text + "\n";
            content = "加密插件异常\n机器 IP：" + Logger.host + " \n " + content;
            content = " \n " + content;

            DingTalkDto dingTalk = new DingTalkDto();
            dingTalk.setToken("");
            dingTalk.setText(content);
            dingTalk.setTitle("数据库加密插件异常");
            dingTalk.setMsgType("text");
           process(dingTalk);
        } catch (Exception e) {
        } finally {
        }
    }


    public static OapiRobotSendResponse process(DingTalkDto dingTalk) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(dingTalkUrlPre );
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");

        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(dingTalk.getText());
        request.setText(text);
        // 被@人的手机号(在text内容里要有@手机号)
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();


        String[] mobiles = "18458195149".split(",");
        at.setAtMobiles(Arrays.asList(mobiles));

        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        return response;
    }

    public static void main(String[] args) {
        sendText("xxx");
    }


}
