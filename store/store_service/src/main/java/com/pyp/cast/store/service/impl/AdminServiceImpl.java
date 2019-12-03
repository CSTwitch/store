package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IAdminDao;
import com.pyp.cast.store.domain.PO.Admin;
import com.pyp.cast.store.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IAdminDao adminDao;
    @Override
    public Admin findAdminByUserName(String adname) throws Exception {
        return adminDao.findAdminByUserName(adname);
    }
}
