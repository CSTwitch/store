package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IUserDao {
    @Select("select * from user where username=#{username}")
    User findUserByUserName(String username)throws Exception;

    @Insert("insert user values (#{uid},#{username},#{password},#{name},#{sex},#{email},#{telephone})")
    void addUser(User user)throws Exception;
}
