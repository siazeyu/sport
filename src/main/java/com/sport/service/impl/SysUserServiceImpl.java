package com.sport.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sport.entity.SysUser;
import com.sport.mapper.SysUserMapper;
import com.sport.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Siaze
 * @since 2022-12-02
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
