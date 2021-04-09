package com.admin.crawler.service.impl;

import com.admin.crawler.mapper.TestUserMapper;
import com.admin.crawler.service.TestUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 * 项目用户 服务类
 * </p>
 *
 * @author quyixiao
 * @since 2021-01-28
 */
@Slf4j
@Service
public class TestUserServiceImpl implements TestUserService {


    @Autowired
    private TestUserMapper testUserMapper;

    @Override
    @Async("asyncTaskExecutor")
    public void testAsync( int i ) {
        Random random = new Random();
        int sleep = random.nextInt(1000);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("i  = " + i  + " , sleep " + sleep);
    }


}
