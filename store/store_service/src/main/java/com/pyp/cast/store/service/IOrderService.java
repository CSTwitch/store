package com.pyp.cast.store.service;

import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.utils.PageModel;

import java.util.List;

public interface IOrderService {
    List<OrderItem> findNoPaidOrderItemsByUid(String uid)throws Exception;
    void addCartItemByUid(User user,String pid,String quantity) throws Exception;

    void deleteCartItemByUidAndPid(String uid, String pid)throws Exception;

    void deleteAllCartByUid(String uid) throws Exception;

    Order findNoPaidOrderByUid(String uid)throws Exception;

    void updateOrderStatuByOid(Order order)throws Exception;

    PageModel findMyOrdersWithpage(String uid, int cur)throws Exception;
}
