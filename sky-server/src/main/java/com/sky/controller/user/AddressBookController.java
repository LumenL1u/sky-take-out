package com.sky.controller.user;

import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/addressBook")
@Tag(name = "地址簿管理")
@Slf4j
@RequiredArgsConstructor
public class AddressBookController {

    private final AddressBookService addressBookService;

    @GetMapping("/list")
    @Operation(summary = "查询当前登录用户的所有地址簿")
    public Result<List<AddressBook>> list() {
        log.info("查询当前登录用户的所有地址簿");
        List<AddressBook> list = addressBookService.listByUserId(BaseContext.getCurrentId());
        return Result.success(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询地址簿")
    public Result<AddressBook> getById(@Parameter(description = "地址簿ID", required = true) @PathVariable Long id) {
        log.info("根据ID查询地址簿：id={}", id);
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    @GetMapping("/default")
    @Operation(summary = "查询默认地址簿")
    public Result<AddressBook> getDefault() {
        log.info("查询默认地址簿");
        AddressBook addressBook = addressBookService.getDefault(BaseContext.getCurrentId());
        return Result.success(addressBook);
    }

    @PostMapping
    @Operation(summary = "新增地址簿")
    public Result<Void> save(@RequestBody AddressBook addressBook) {
        log.info("新增地址簿：{}", addressBook);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.save(addressBook);
        return Result.success();
    }

    @PutMapping("/default")
    @Operation(summary = "设置默认地址簿")
    public Result<Void> setDefault(@Parameter(description = "地址簿ID", required = true) @RequestBody Map<String, Long> map) {
        Long id = map.get("id");
        log.info("设置默认地址簿：id={}", id);
        addressBookService.setDefault(id);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "修改地址簿")
    public Result<Void> update(@RequestBody AddressBook addressBook) {
        log.info("修改地址簿：{}", addressBook);
        addressBookService.updateById(addressBook);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除地址簿")
    public Result<Void> delete(@Parameter(description = "地址簿ID", required = true) Long id) {
        log.info("删除地址簿：id={}", id);
        addressBookService.removeById(id);
        return Result.success();
    }
}
