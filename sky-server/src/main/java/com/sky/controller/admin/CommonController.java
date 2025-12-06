package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Tag(name = "通用接口")
@RequiredArgsConstructor
public class CommonController {
    private final AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public Result<String> upload(@Parameter(description = "文件", required = true) @RequestPart("file") MultipartFile file) {
        try {
            log.info("上传文件：{}", file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            // 获取当前系统日期的字符串,格式为 yyyy/MM
            String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
            // 生成一个新的不重复的文件名
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = dir + "/" + newFileName;
            // 文件上传
            String url = aliOssUtil.upload(bytes, objectName);
            return Result.success("操作成功", url);
        } catch (Exception e) {
            log.error("上传文件失败：", e);
            return Result.error(MessageConstant.UPLOAD_FAILED, null);
        }
    }
}
