package com.pyp.cast.store.service;

import com.pyp.cast.store.domain.PO.Admin;

public interface IAdminService {
    Admin findAdminByUserName(String adname)throws Exception;
}
