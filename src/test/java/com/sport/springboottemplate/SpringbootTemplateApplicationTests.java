package com.sport.springboottemplate;

import com.alibaba.fastjson.JSON;
import com.sport.entity.SysLog;
import com.sport.entity.system.ResultData;
import com.sport.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
@SpringBootTest
class SpringbootTemplateApplicationTests {



    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(ResultData.success(null));
    }

    @Test
    void log(){
        if (log.isDebugEnabled()){
            log.debug("debug");
        }

        log.info("info");
    }

    @Test
    void redis(){
        SysLog sysLog = new SysLog();
        sysLog.setMethod("SSS");
        redisTemplate.opsForValue().set("a", sysLog);
        SysLog a = (SysLog) redisTemplate.opsForValue().get("a");
        System.out.println(JSON.toJSONString(a));
    }

    @Test
    void jwt(){
        JwtUtils.generateToken("111");
        String password = "123456";
        String encodePwd = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodePwd);
        byte[] decode = Base64.getDecoder().decode(encodePwd.getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(decode));
    }
}
