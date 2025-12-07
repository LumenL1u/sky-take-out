package com.sky.controller.user;

import com.sky.constant.RedisConstant;
import com.sky.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@Slf4j
@RequestMapping("/user/shop")
@Tag(name = "店铺管理")
@RequiredArgsConstructor
public class ShopController {
    public static final String KEY = RedisConstant.SHOP_STATUS;
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/status")
    @Operation(summary = "获取店铺状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        if (status == null) {
            status = 0;
            redisTemplate.opsForValue().set(KEY, status);
        }
        log.info("获取店铺状态：{}", status == 1 ? "开启" : "关闭");
        return Result.success(status);
    }
}
