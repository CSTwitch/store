package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAdminUserDao {
    @Select("select count(*) from user")
    int findTotalRecords()throws Exception;

    @Select("select * from `user` limit  #{startIndex},#{pageSize}")
    List<User> findProductsByCidWithPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize)throws Exception;

    @Select("Select count(*) from `browse` where uid=#{uid}")
    int findBrowseTotalRecords(String uid);

    @Select("select * from browse where uid=#{uid} limit  #{startIndex},#{pageSize}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "total", column = "total"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<Browse> findBrowseProductWithPage(@Param("uid") String uid, @Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
}
