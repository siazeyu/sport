package com.sport.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Siaze
 * @date 2021/11/9
 */
@Component
public class JwtUtils {

    private static String secret ;
    private final static Long expiration = 60 * 60 * 60L;
    /**
     *  创建token
     * @param username 用户名
     * @return token
     *  */
    public static String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /** 使用方法注入
     * @param secret 密钥
     * */
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JwtUtils.secret = secret;
    }

    /**
     *  从token中获取用户名
     * @param token 用户token
     * @return 从token获取的用户名
     * */
    public static String getUserNameFromToken(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 是否已过期
     * @param token 判断的token
     * @return 结果
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 获取用户token信息
     * @param token 用户token
     * @return 用户token解码后的对象
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取账号 而不是用户姓名
     * @return
     */
    public static String getLoginUsername(){
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return "system";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
}
