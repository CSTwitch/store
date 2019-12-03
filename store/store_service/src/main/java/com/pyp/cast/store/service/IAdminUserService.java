package com.pyp.cast.store.service;

import com.pyp.cast.store.utils.PageModel;

public interface IAdminUserService {
    PageModel findAllProductWithpage(int curNum)throws Exception;

    PageModel findBrowseProductWithPage(String uid, int cur)throws Exception;
}
