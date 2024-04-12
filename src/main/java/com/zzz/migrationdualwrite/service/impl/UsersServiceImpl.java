package com.zzz.migrationdualwrite.service.impl;

import com.zzz.migrationdualwrite.entity.Users;
import com.zzz.migrationdualwrite.service.UsersService;
import com.zzz.migrationdualwrite.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author 张忠振
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

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
        StringJoiner stringJoiner = new StringJoiner(" || ");
        for (Users users : usersList) {
            stringJoiner.add(users.getId() + users.getName());
        }
        return stringJoiner.toString();
    }

    @Override
    public Users getUserById(Integer id) {
        return null;
    }
}




