package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {
    @Select("select * from `order` where uid=#{uid} and statu=0")
    Order findNoPaidOrderByUid(@Param("uid") String uid)throws Exception;

    @Insert("insert `order`(oid,uid) values (#{oid},#{uid})")
    void addNoPaidOrderByUid(@Param("oid") String oid,@Param("uid") String uid)throws Exception;

    @Insert("insert orderitem(itemid, quantity, total_price, pid, oid) VALUES (#{itemid},#{quantity},#{total_price},#{pid},#{oid});")
    void addCartItemByUid(@Param("itemid")String itemid,@Param("quantity") String quantity,@Param("total_price") int total_price,@Param("pid") String pid,@Param("oid") String oid)throws Exception;

    @Select("select * from orderitem where oid in (select oid from `order` where uid=#{uid} and statu=0)")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findNoPaidOrderItemsByUid(@Param("uid") String uid);

    @Select("select * from orderitem where oid=#{oid} and pid=#{pid}")
    OrderItem findOrderItemByPidAndOid(@Param("pid") String pid,@Param("oid") String oid);

    @Update("update orderitem set quantity=#{quantity},total_price=#{total_price} where itemid=#{itemid}")
    void updateOrderItemByPidAndOid(@Param("quantity") Integer num,@Param("total_price") Integer total,@Param("itemid") String itemid);

    @Delete("delete from orderitem where pid=#{pid} and oid in (select oid from `order` where uid=#{uid} and statu=0)")
    void deleteCartItemByUidAndPid(@Param("uid") String uid,@Param("pid") String pid)throws Exception;

    @Delete("delete from orderitem where oid in (select oid from `order` where uid=#{uid} and statu=0)")
    void deleteAllCartByUid(@Param("uid") String uid)throws Exception;

    @Update("update `order` set statu=#{statu},ordertime=#{ordertime},total_price=#{total_price},adress=#{adress},name=#{name},email=#{email} where oid=#{oid}")
    void updateOrderStatuByOid(@Param("oid") String oid,@Param("ordertime") String ordertime,@Param("total_price") String total_price,@Param("adress") String adress,@Param("name") String name,@Param("email") String email, @Param("statu")String statu);

    @Select("select count(*) from `order` where uid=#{uid}")
    int findTotalRecords(String uid)throws Exception;

    @Select("select * from `order` where uid=#{uid} limit  #{startIndex},#{pageSize}")
    List<Order> findMyOrdersWithpage(@Param("uid")String uid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    @Select("select * from orderitem where oid=#{oid}")
    @Results({
            @Result(id = true, property = "itemid", column = "itemid"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "total_price", column = "total_price"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    List<OrderItem> findMyOrderItemsByOid(String oid)throws Exception;

    @Delete("delete from `orderitem` where pid=#{pid}")
    void deleteProductByPid(@Param("pid") String pid)throws Exception;
}
