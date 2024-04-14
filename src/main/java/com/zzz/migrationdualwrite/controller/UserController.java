package com.zzz.migrationdualwrite.controller;

import com.zzz.migrationdualwrite.aop.DataSourceSwitch;
import com.zzz.migrationdualwrite.config.DataSourceCommonProperties;
import com.zzz.migrationdualwrite.entity.Users;
import com.zzz.migrationdualwrite.enums.DataSourceType;
import com.zzz.migrationdualwrite.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Zzz
 * @date: 2024/4/11 17:10
 * @description:
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private DataSourceCommonProperties dataSourceCommonProperties;

    @PostMapping("/createUser/{un}")
    public String createUser(@PathVariable("un") String un) {
        return usersService.createUser(un);
    }

    @GetMapping("/getAllUsers")
    @DataSourceSwitch(DataSourceType.REMOTE_DATASOURCE)
    public String getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/getLocalAllUsers")
    @DataSourceSwitch(DataSourceType.REMOTE_DATASOURCE)
    public String getLocalAllUsers() {
        return usersService.getAllUsers();
    }


    @GetMapping("/getUserById/{id}")
    public Users getUserById(@PathVariable("id") Integer id) {
        return usersService.getUserById(id);
    }

    @GetMapping("/queryCommon")
    public void queryCommon() {
        System.out.println(dataSourceCommonProperties.getUsername());
    }

}
