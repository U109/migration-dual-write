package com.zzz.migrationdualwrite.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Zzz
 * @date: 2024/4/11 15:28
 * @description:
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello";
    }

}
