package com.zzz.migrationdualwrite.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 张忠振
 * @TableName users
 */
@Data
public class Users implements Serializable {

    private Integer id;

    private String name;

    private String email;

    private String password;
}