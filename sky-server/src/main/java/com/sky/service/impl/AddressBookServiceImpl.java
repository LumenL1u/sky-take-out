package com.sky.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ming
* @description 针对表【address_book(地址簿)】的数据库操作Service实现
* @createDate 2025-12-09 15:13:54
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService{

    @Override
    public List<AddressBook> listByUserId(Long currentId) {
        return list(Wrappers.<AddressBook>lambdaQuery()
                .eq(AddressBook::getUserId, currentId));
    }

    @Override
    public AddressBook getDefault(Long currentId) {
        return getOne(Wrappers.<AddressBook>lambdaQuery()
                .eq(AddressBook::getUserId, currentId)
                .eq(AddressBook::getIsDefault, 1));
    }

    @Override
    public void setDefault(Long id) {
        update(Wrappers.<AddressBook>lambdaUpdate()
                .eq(AddressBook::getIsDefault, 1)
                .set(AddressBook::getIsDefault, 0));
        update(Wrappers.<AddressBook>lambdaUpdate()
                .eq(AddressBook::getId, id)
                .set(AddressBook::getIsDefault, 1));
    }
}




