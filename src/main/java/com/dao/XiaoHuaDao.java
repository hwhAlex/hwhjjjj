package com.dao;

import com.model.XiaoHua;

/**
 * Created by honor on 2017/12/5.
 */
public interface XiaoHuaDao {
    public void insert(XiaoHua xiaoHua);
    public XiaoHua getByLink(String link);

}
