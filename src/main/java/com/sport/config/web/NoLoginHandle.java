package com.sport.config.web;

import com.sport.entity.system.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.sport.entity.system.ReturnCode.ACCESS_DENIED;

/**
 * @author Siaze
 * @date 2021/11/8
 */
@Component
public class NoLoginHandle implements AuthenticationEntryPoint {
    /**
     * 没登录的情况
     * @param request 请求对象
     * @param response 返回对象
     * @param authException 认证异常
     * @throws IOException IO异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.print( ResultData.fail(ACCESS_DENIED.getCode(), ACCESS_DENIED.getMessage()));
        out.flush();
        out.close();
    }
}