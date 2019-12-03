package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {
    @Select("select count(*) from product")
    int findTotalRecords();

    @Select("select * from product limit  #{startIndex},#{pageSize}")
    List<Product> findProductsByCidWithPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    @Select("select * from product where pid=#{pid}")
    Product findProductByPid(@Param("pid") String pid) throws Exception;

    @Select("select price from product where pid=#{pid}")
    Integer findProPriceByPid(String pid) throws Exception;

    @Update("update product set pname=#{pname},price=#{price},pimage=#{pimage},pdesc=#{pdesc} where pid=#{pid}")
    void updateProductByPid(Product product)throws Exception;

    @Delete("delete from `product` where pid=#{pid}")
    void deleteProductByPid(@Param("pid") String pid)throws Exception;

    //查询商品销售量
    @Select("select sum(quantity) from orderitem where pid=#{pid} and oid in (select oid from `order` where statu>0)")
    Integer findSaleProductTotalByPid(@Param("pid") String pid);

    @Insert("insert product values (#{pid},#{pname},#{price},#{pimage},#{pdesc})")
    void addProduct(Product product);


    @Select("select * from browse where uid=#{uid} and pid=#{pid}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "total", column = "total"),
            @Result(property = "product", column = "pid", javaType = Product.class, one = @One(select = "com.pyp.cast.store.dao.IProductDao.findProductByPid")),
    })
    Browse findBrowseProduct(@Param("uid") String uid,@Param("pid") String pid)throws Exception;

    @Insert("insert into browse values (#{uid},#{pid},1)")
    void addBrowseProduct(@Param("uid") String uid,@Param("pid") String pid);

    @Update("update browse set total=#{total} where uid=#{uid} and pid=#{pid}")
    void updateBrowseProductTotal(@Param("uid") String uid,@Param("pid") String pid,@Param("total") int total);

    @Delete("delete from itcast_store.browse where pid=#{pid}")
    void deleteBrowseByPid(@Param("pid") String pid);
}
