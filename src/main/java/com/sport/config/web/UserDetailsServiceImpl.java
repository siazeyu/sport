package com.sport.config.web;

import com.sport.entity.SysUser;
import com.sport.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Siaze
 * @description
 * @date 2021/11/8
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    /** 密码加密工具 */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserService iSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser =  iSysUserService.getById(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("账号或密码错误");
        }
        //username为用户输入的账号用户登录会调用该方法
        List<GrantedAuthority> authorities = new ArrayList<>();
        //设置身份
//        authorities.add(new SimpleGrantedAuthority(""));
        //返回账号密码身份与用户输入的比较(只比较密码)
        return new User(username, passwordEncoder.encode("sysUser.getPassword()"), authorities);
    }
}
