package com.pyp.cast.store.domain.PO;

import java.util.List;

public class Browse {
    private String uid;
    private Product product;
    private int total;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Browse{" +
                "uid=" + uid +
                ", product=" + product +
                ", total=" + total +
                '}';
    }
}
