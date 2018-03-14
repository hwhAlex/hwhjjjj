package com.service;

import com.model.DarenWeibo;
import com.param.DarenParam;

/**
 * Created by honor on 2017/11/17.
 */
public interface DarenService {

    public Integer addData(DarenParam param);

    public Integer addWeibo(String weiboData);
}
