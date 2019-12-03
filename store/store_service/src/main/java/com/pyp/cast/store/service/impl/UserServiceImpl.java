package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IUserDao;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public User findUserByUserName(String username) throws Exception {
        return userDao.findUserByUserName(username);
    }

    @Override
    public void addUser(User user) throws Exception {
        userDao.addUser(user);
    }
}
