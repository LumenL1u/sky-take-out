package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.AddressBook;

import java.util.List;

/**
* @author ming
* @description 针对表【address_book(地址簿)】的数据库操作Service
* @createDate 2025-12-09 15:13:54
*/
public interface AddressBookService extends IService<AddressBook> {

    List<AddressBook> listByUserId(Long currentId);

    AddressBook getDefault(Long currentId);

    void setDefault(Long id);
}
