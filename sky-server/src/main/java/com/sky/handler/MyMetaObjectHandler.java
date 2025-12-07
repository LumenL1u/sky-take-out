package com.sky.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sky.context.BaseContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充公共字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 只在字段存在时才填充，避免对没有这些字段的实体报错
        if (metaObject.hasSetter("createTime")) {
            metaObject.setValue("createTime", LocalDateTime.now());
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
        if (metaObject.hasSetter("createUser")) {
            metaObject.setValue("createUser", BaseContext.getCurrentId());
        }
        if (metaObject.hasSetter("updateUser")) {
            metaObject.setValue("updateUser", BaseContext.getCurrentId());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 只在字段存在时才填充，避免对没有这些字段的实体报错
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", LocalDateTime.now());
        }
        if (metaObject.hasSetter("updateUser")) {
            metaObject.setValue("updateUser", BaseContext.getCurrentId());
        }
    }
}
