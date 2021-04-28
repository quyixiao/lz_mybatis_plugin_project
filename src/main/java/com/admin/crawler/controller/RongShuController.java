package com.admin.crawler.controller;

import com.admin.crawler.entity.UserBo;
import com.admin.crawler.utils.R;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rongshu")
public class RongShuController {

    @RequestMapping("/enter")
    public R enter(@RequestBody UserBo bo,String username) {
        System.out.println("======username=======" + bo.getUsername());
        System.out.println("phone===========" + bo.getPhone());
        //System.out.println(JSON.toJSONString(bo));
        return R.ok("kwkw ");
    }

    @PostMapping("/enter1")
    public R enter1(@RequestBody  UserBo bo ) {
        System.out.println(JSON.toJSONString(bo));
        return R.ok("kwkw ");
    }
}
