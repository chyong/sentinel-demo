package com.chenyong.sentinel.mapper;

import com.chenyong.sentinel.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from t_user t where name = ${name}")
    User findByUserName(@Param("name") String name);
}
