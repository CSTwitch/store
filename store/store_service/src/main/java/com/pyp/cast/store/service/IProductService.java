package com.pyp.cast.store.service;

import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.utils.PageModel;

import java.util.List;

public interface IProductService {
    PageModel findAllProductWithpage(int curNum) throws Exception;

    Product findProductByPid(String uid,String pid)throws Exception;
    Product findProductByPid02(String pid)throws Exception;

    void updateProductByPid(Product product)throws Exception;

    void deleteProductByPid(String pid)throws Exception;

    void addProduct(Product product)throws Exception;

}
