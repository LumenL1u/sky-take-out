package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.constant.MessageConstant;
import com.sky.constant.WeChatConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ming
 * @description 针对表【user(用户信息)】的数据库操作Service实现
 * @createDate 2025-12-07 18:22:13
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final WeChatProperties wechatProperties;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信接口服务，获取到当前用户的openid
        Map<String, String> params = new HashMap<>();
        params.put(WeChatConstant.APPID_KEY, wechatProperties.getAppid());
        params.put(WeChatConstant.SECRET_KEY, wechatProperties.getSecret());
        params.put(WeChatConstant.CODE_KEY, userLoginDTO.getCode());
        params.put(WeChatConstant.GRANT_TYPE_KEY, WeChatConstant.GRANT_TYPE_VALUE);
        String result = HttpClientUtil.doGet(WeChatConstant.WX_LOGIN_URL, params);

        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString(WeChatConstant.OPENID_KEY);

        // 判断openid是否为空，如果为空表示登录失败，抛出异常
        if (openid == null || openid.isEmpty()) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        // 根据openid查询数据库是否有当前用户，如果没有则注册新用户
        User user = this.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getOpenid, openid));
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .build();
            save(user);
        }
        return user;
    }
}




