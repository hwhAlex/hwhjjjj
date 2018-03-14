package com.dao;

import com.model.OABaoWenUser;

import java.util.List;

/**
 * Created by honor on 2017/12/12.
 */
public interface OABaowenUserDao {

    public void insert(OABaoWenUser oaBaoWenUser);
    public List<OABaoWenUser> getAll();
}
