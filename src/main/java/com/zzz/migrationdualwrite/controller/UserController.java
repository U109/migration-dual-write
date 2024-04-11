package com.zzz.migrationdualwrite.controller;

import com.zzz.migrationdualwrite.entity.Users;
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

    @PostMapping("/createUser/{un}")
    public String createUser(@PathVariable("un") String un) {
        return usersService.createUser(un);
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers() {
        return usersService.getAllUsers();
    }
}
