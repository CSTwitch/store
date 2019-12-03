package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IAdminOrderDao;
import com.pyp.cast.store.dao.IAdminUserDao;
import com.pyp.cast.store.domain.PO.Browse;
import com.pyp.cast.store.domain.PO.Product;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IAdminUserService;
import com.pyp.cast.store.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    private IAdminUserDao adminUserDao;

    @Override
    public PageModel findAllProductWithpage(int curNum) throws Exception {
        int totalRecords=adminUserDao.findTotalRecords();
        PageModel pm=new PageModel(curNum,totalRecords,12);
        List<User> users=adminUserDao.findProductsByCidWithPage(pm.getStartIndex(),pm.getPageSize());
        pm.setList(users);
        pm.setUrl("/product/findAllProduct.do");
        return pm;
    }

    @Override
    public PageModel findBrowseProductWithPage(String uid, int cur) throws Exception {
        int totalRecords=adminUserDao.findBrowseTotalRecords(uid);
        PageModel pm=new PageModel(cur,totalRecords,12);
        List<Browse> browses=adminUserDao.findBrowseProductWithPage(uid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(browses);
        return pm;
    }
}
