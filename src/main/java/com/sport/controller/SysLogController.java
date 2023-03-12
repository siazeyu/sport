package com.sport.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sport.entity.SysLog;
import com.sport.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 请求日志表 前端控制器
 * </p>
 *
 * @author Siaze
 * @since 2022-07-13
 */
@RestController
@RequestMapping("/sys-log")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @GetMapping
    Page<SysLog> getSysLogs(Integer current){
        Page<SysLog> page = new Page<>(current == null ? 1 : current,5);
        return sysLogService.page(page);
    }

}
