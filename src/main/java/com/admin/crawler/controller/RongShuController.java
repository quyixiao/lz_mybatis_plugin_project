package com.admin.crawler.controller;

import com.admin.crawler.entity.UserBo;
import com.admin.crawler.utils.R;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rongshu")
public class RongShuController {

    @RequestMapping("/enter")
    public R enter(String username,String phone ) {
        System.out.println("======username=======" + username);
        System.out.println("phone===========" + phone);
        //System.out.println(JSON.toJSONString(bo));
        return R.ok("kwkw ");
    }

    @PostMapping("/enter1")
    public R enter1(@RequestBody  UserBo bo ) {
        System.out.println(JSON.toJSONString(bo));
        return R.ok("kwkw ");
    }
}
