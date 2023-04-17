package com.sport.controller;

import com.sport.config.exception.NoUserException;
import com.sport.entity.SysUser;
import com.sport.service.ISysUserService;
import com.sport.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static com.sport.entity.system.ReturnCode.USERNAME_OR_PASSWORD_ERROR;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     * @param params
     * @return
     */
    @PostMapping("/login")
    @Transactional
    public SysUser login(@RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");
        SysUser user = sysUserService.getById(username);
        String encodePwd = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
        if (user != null && user.getPassword().equals(encodePwd)){
            user.setToken(JwtUtils.generateToken(username));
            user.setLoginDate(new Date());
            sysUserService.save(user);
            return user;
        }
        throw new NoUserException(USERNAME_OR_PASSWORD_ERROR.getMessage());
    }

    @PostMapping("/register")
    public SysUser register(@RequestBody SysUser user){
        String encodePwd = Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(encodePwd);
        sysUserService.save(user);
        return user;
    }


}
