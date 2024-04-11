package com.zzz.migrationdualwrite.mapper;

import com.zzz.migrationdualwrite.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 张忠振
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    void createUser(@Param("user") Users users);

    List<Users> getAllUsers();
}




