package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.Admin;
import org.apache.ibatis.annotations.Select;

public interface IAdminDao {
    @Select("select * from admin where adname=#{adname}")
    Admin findAdminByUserName(String adname)throws Exception;
}
