package com.pyp.cast.store.domain.PO;

import java.util.List;

public class Order {
    private String oid;
    private String ordertime;
    private String total_price;
    private String adress;
    private String name;
    private String email;
    private String statu;  //statu=0：购物车内容  statu=1：订单已支付  statu=2：卖家已发货  statu=3：买家确认收货
    //该订单对应的用户
    private User user;
    //该订单对应的订单项
    private List<OrderItem> list;

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return list;
    }

    public void setOrderItems(List<OrderItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", total_price='" + total_price + '\'' +
                ", adress='" + adress + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", statu='" + statu + '\'' +
                ", user=" + user +
                ", list=" + list +
                '}';
    }
}
