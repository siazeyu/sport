package com.sport.config.web;

import com.sport.entity.system.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.sport.entity.system.ReturnCode.USERNAME_OR_PASSWORD_ERROR;

/**
 * @author Siaze
 * @date 2021/11/9
 */
@Component
public class LoginFailHandle implements AuthenticationFailureHandler {
    /**
     * 登录失败或超时的情况
     * @param httpServletRequest 请求对象
     * @param httpServletResponse 返回对象
     * @param e 认证异常
     * @throws IOException IO异常
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.print( ResultData.fail(USERNAME_OR_PASSWORD_ERROR.getCode(), USERNAME_OR_PASSWORD_ERROR.getMessage()));
        out.flush();
        out.close();
    }
}
