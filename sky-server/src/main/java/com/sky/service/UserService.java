package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @author ming
 * @description 针对表【user(用户信息)】的数据库操作Service
 * @createDate 2025-12-07 18:22:13
 */
public interface UserService extends IService<User> {

    /**
     * 微信登录
     *
     * @param userLoginDTO 微信登录DTO
     * @return 用户信息
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
