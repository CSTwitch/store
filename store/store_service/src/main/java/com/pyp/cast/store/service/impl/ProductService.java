package com.pyp.cast.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.pyp.cast.store.dao.IOrderDao;
import com.pyp.cast.store.dao.IProductDao;
import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.service.IProductService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private IOrderDao orderDao;

    @Override
    public PageModel findAllProductWithpage(int curNum) throws Exception {
        int totalRecords=productDao.findTotalRecords();
        PageModel pm=new PageModel(curNum,totalRecords,12);
        List<Product> products=productDao.findProductsByCidWithPage(pm.getStartIndex(),pm.getPageSize());
        for (Product product:products) {
            product.setTotal(productDao.findSaleProductTotalByPid(product.getPid()));
        }
        pm.setList(products);
        pm.setUrl("/product/findAllProduct.do");
        return pm;
    }


    @Override
    public Product findProductByPid(String uid,String pid) throws Exception {
        Browse browse = productDao.findBrowseProduct(uid,pid);
        if (browse == null){
            productDao.addBrowseProduct(uid,pid);
        }else {
            productDao.updateBrowseProductTotal(uid,pid,browse.getTotal()+1);
        }
        return productDao.findProductByPid(pid);
    }

    @Override
    public Product findProductByPid02(String pid) throws Exception {
        return productDao.findProductByPid(pid);
    }

    @Override
    public void updateProductByPid(Product product) throws Exception {
        productDao.updateProductByPid(product);
    }

    @Override
    public void deleteProductByPid(String pid) throws Exception {
        productDao.deleteBrowseByPid(pid);
        orderDao.deleteProductByPid(pid);
        productDao.deleteProductByPid(pid);
    }

    @Override
    public void addProduct(Product product) throws Exception {
        product.setPid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        productDao.addProduct(product);
    }
}
