package com.pyp.cast.store.domain.PO;

public class OrderItem {
    private String itemid;
    private String quantity;
    private String total_price;
    //该订单项对应的商品信息
    private Product product;
    //该订单项对应的订单
    private Order order;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemid='" + itemid + '\'' +
                ", quantity='" + quantity + '\'' +
                ", total_price='" + total_price + '\'' +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
