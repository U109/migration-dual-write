package com.zzz.migrationdualwrite.service;

import com.zzz.migrationdualwrite.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 张忠振
 */
public interface UsersService extends IService<Users> {

    String createUser(String userName);

    String getAllUsers();

    Users getUserById(Integer id);
}
