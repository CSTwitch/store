package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IAdminOrderDao {
    @Select("select count(*) from `order` where statu=#{statu}")
    int findTotalRecords(String statu)throws Exception;

    @Select("select * from `orderitem` where oid=#{oid}")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findOrderItemsByOid(@Param("oid") String oid) throws Exception;

    @Select("select * from `order` where statu=#{statu} limit #{startIndex},#{pageSize}")
    List<Order> findProductsByCidWithPage(@Param("statu") String statu,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize)throws Exception;

    @Update("update `order` set statu=#{statu} where oid=#{oid}")
    void updateOrderStatuByOid(@Param("statu") int statu,@Param("oid") String oid)throws Exception;

    @Select("select email from `order` where oid=#{oid}")
    String findEmailByOid(@Param("oid") String oid)throws Exception;
}
