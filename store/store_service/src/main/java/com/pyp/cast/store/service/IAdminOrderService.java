package com.pyp.cast.store.service;

import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.utils.PageModel;

import java.util.List;

public interface IAdminOrderService {
    PageModel findAllOrdersWithPage(int curNum,String statu)throws Exception;

    List<OrderItem> findOrderItemsByOid(String oid)throws Exception;

    void updateOrderStatuByOid(String oid,String statu)throws Exception;
}
