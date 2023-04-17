package com.sport.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Siaze
 * @since 2022-12-02
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    @JSONField(serialize = false)
    private String password;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("用户创建日期")
    private Date createDate;

    @ApiModelProperty("最后登录日期")
    private Date loginDate;

    @ApiModelProperty("权限标志")
    private Integer role;

    @TableField(exist = false)
    private String token;

    @ApiModelProperty("身高")
    private Integer height;

    @ApiModelProperty("体重")
    private Float weight;

}
