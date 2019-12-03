package com.pyp.cast.store.service;

import com.pyp.cast.store.domain.PO.User;

public interface IUserService {
    User findUserByUserName(String username)throws Exception;

    void addUser(User user)throws Exception;
}
