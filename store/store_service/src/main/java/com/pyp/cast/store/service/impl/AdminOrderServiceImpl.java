package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IAdminOrderDao;
import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IAdminOrderService;
import com.pyp.cast.store.utils.MailUtils;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminOrderServiceImpl implements IAdminOrderService {
    @Autowired
    private IAdminOrderDao adminOrderDao;

    @Override
    public PageModel findAllOrdersWithPage(int curNum,String statu) throws Exception {
        int totalRecords=adminOrderDao.findTotalRecords(statu);
        PageModel pm=new PageModel(curNum,totalRecords,12);
        List<Order> orders=adminOrderDao.findProductsByCidWithPage(statu,pm.getStartIndex(),pm.getPageSize());
        //将该订单的订单项封装进去
        for (Order order:orders) {
            order.setList(adminOrderDao.findOrderItemsByOid(order.getOid()));
        }
        pm.setList(orders);
        return pm;
    }

    @Override
    public List<OrderItem> findOrderItemsByOid(String oid) throws Exception {
        return adminOrderDao.findOrderItemsByOid(oid);
    }

    @Override
    public void updateOrderStatuByOid(String oid,String statu) throws Exception {
        Integer s = Integer.valueOf(statu);
        if (statu.equals("2")){
            String email = adminOrderDao.findEmailByOid(oid);
            //发送邮件给客户签收
            MailUtils.sendMail(email,oid);
            //更新订单状态为发货状态
        }
        adminOrderDao.updateOrderStatuByOid(s,oid);
    }
}
