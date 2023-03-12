package com.sport.config.aspect;


import com.alibaba.fastjson.JSON;
import com.sport.entity.SysLog;
import com.sport.service.ISysLogService;
import com.sport.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    ISysLogService iSysLogService;

    @Pointcut("@annotation(com.sport.annotation.WebLog)")
    public void logPrint(){

    }



    @AfterReturning(pointcut = "logPrint()", returning = "object")
    public void doAfterReturning(JoinPoint joinPoint, final Object object){
        handleLog(joinPoint, null, object);
    }


    @AfterThrowing(pointcut = "logPrint()", throwing = "e")
    public void doThrow(JoinPoint joinPoint, Throwable e){
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Throwable e, Object jsonResult)
    {

        try
        {
            SysLog sysLog = new SysLog();
            // 获取当前的用户
            // 请求的地址
            String ip = ServletUtils.getIpRequestAddress();
            sysLog.setIp(ip);
            sysLog.setRequestUrl(ServletUtils.getRequest().getRequestURI());


            if (e != null)
            {
                sysLog.setStatus(false);
                sysLog.setErrMsg(e.getClass().getName() + " : " + e.getMessage());
            }else {
                sysLog.setStatus(true);

                sysLog.setData(jsonResult == null ? null : jsonResult.toString());
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            sysLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 设置请求参数
            sysLog.setParams(ServletUtils.getRequestParams());
            log.info(JSON.toJSONString(sysLog));
            // 保存数据库
            iSysLogService.save(sysLog);
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }


}
