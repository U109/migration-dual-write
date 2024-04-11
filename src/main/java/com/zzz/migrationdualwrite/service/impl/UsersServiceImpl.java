package com.zzz.migrationdualwrite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.migrationdualwrite.entity.Users;
import com.zzz.migrationdualwrite.service.UsersService;
import com.zzz.migrationdualwrite.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 张忠振
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createUser(String userName) {
        Users users = new Users();
        users.setEmail("1098598203@qq.com");
        users.setName(userName);
        users.setPassword("111111");
        usersMapper.createUser(users);
        return "success";
    }

    @Override
    public String getAllUsers() {

        List<Users> usersList = usersMapper.getAllUsers();
        return "";
    }
}




