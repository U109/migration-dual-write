package com.zzz.migrationdualwrite.service;

import com.zzz.migrationdualwrite.entity.Users;

/**
 * @author 张忠振
 */
public interface UsersService {

    String createUser(String userName);

    String getAllUsers();

    Users getUserById(Integer id);
}
