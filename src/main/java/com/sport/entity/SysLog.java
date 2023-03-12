package com.sport.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  请求日志表
 * </p>
 *
 * @author Siaze
 * @since 2022-07-13
 */
@Getter
@Setter
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 调用的方法
     */
    private String method;

    /**
     * 请求者的IP
     */
    private String ip;

    /**
     * 请求的url
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求方式（GET、POST...)
     */
    private String requestMethod;

    /**
     * 请求者
     */
    private String requestUser;

    /**
     * 过程是否发生异常
     */
    private Boolean status;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 方法返回值
     */
    private String data;

    /**
     * 发生时间
     */
    private Date time;

}
