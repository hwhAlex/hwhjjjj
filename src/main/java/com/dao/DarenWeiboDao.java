package com.dao;

import com.model.DarenWeibo;

/**
 * Created by honor on 2018/1/4.
 */
public interface DarenWeiboDao {
    public void insert(DarenWeibo darenWeibo);
    public DarenWeibo getById(Integer id);
    public DarenWeibo getByNick(String nick);
    public void update(DarenWeibo darenWeibo);

}
