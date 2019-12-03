package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IOrderDao;
import com.pyp.cast.store.dao.IProductDao;
import com.pyp.cast.store.domain.PO.Order;
import com.pyp.cast.store.domain.PO.OrderItem;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IOrderService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IProductDao productDao;

    @Override
    public List<OrderItem> findNoPaidOrderItemsByUid(String uid) throws Exception {
        return orderDao.findNoPaidOrderItemsByUid(uid);
    }

    @Override
    public void addCartItemByUid(User user,String pid,String quantity) throws Exception {
        //1、如果该用户下没有未支付订单的记录，则添加一条未支付的订单给该用户
        Order order = orderDao.findNoPaidOrderByUid(user.getUid());
        String itemid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        int total_price = Integer.parseInt(quantity)*productDao.findProPriceByPid(pid);
        if (order == null){
            order = new Order();
            String oid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            order.setOid(oid);
            order.setUser(user);
            orderDao.addNoPaidOrderByUid(order.getOid(),order.getUser().getUid());
            //将订单项加入此未支付项中
            orderDao.addCartItemByUid(itemid,quantity,total_price,pid, oid);
        }else {
            //如果存在未支付订单，则查询该订单下的订单项是否存在相同的产品信息。如果有，更新订单项信息；若无，则直接添加
            OrderItem orderItem = orderDao.findOrderItemByPidAndOid(pid,order.getOid());
            if (orderItem != null){
                Integer num = Integer.parseInt(quantity)+Integer.parseInt(orderItem.getQuantity());
                Integer total = num*productDao.findProPriceByPid(pid);
                orderDao.updateOrderItemByPidAndOid(num,total,orderItem.getItemid());
            }else {
                //2、如果该用户下有未支付订单的记录，则将此购物项添加进该条未支付订单
                orderDao.addCartItemByUid(itemid,quantity,total_price,pid, order.getOid());
            }
        }
    }

    @Override
    public void deleteCartItemByUidAndPid(String uid, String pid) throws Exception {
        orderDao.deleteCartItemByUidAndPid(uid,pid);
    }

    @Override
    public void deleteAllCartByUid(String uid) throws Exception {
        orderDao.deleteAllCartByUid(uid);
    }

    @Override
    public Order findNoPaidOrderByUid(String uid) throws Exception {
        return orderDao.findNoPaidOrderByUid(uid);
    }

    @Override
    public void updateOrderStatuByOid(Order order) throws Exception {
        String oid = order.getOid();
        String ordertime = order.getOrdertime();
        String total_price = order.getTotal_price();
        String adress = order.getAdress();
        String name = order.getName();
        String statu = order.getStatu();
        String email = order.getEmail();
        orderDao.updateOrderStatuByOid(oid,ordertime,total_price,adress,name,email,statu);
    }

    @Override
    public PageModel findMyOrdersWithpage(String uid, int curNum) throws Exception {
        int totalRecords=orderDao.findTotalRecords(uid);
        PageModel pm=new PageModel(curNum,totalRecords,3);
        List<Order> orders=orderDao.findMyOrdersWithpage(uid,pm.getStartIndex(),pm.getPageSize());
        for (Order order:orders) {
            List<OrderItem> orderItems = orderDao.findMyOrderItemsByOid(order.getOid());
            order.setOrderItems(orderItems);
        }
        System.out.println(orders);

        pm.setList(orders);
        pm.setUrl("/order/findMyOrdersWithPage.do");
        return pm;
    }

}
