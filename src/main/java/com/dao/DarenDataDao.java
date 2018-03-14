package com.dao;

import com.model.DarenData;
import org.apache.ibatis.annotations.Param;

/**
 * Created by honor on 2017/11/17.
 */
public interface DarenDataDao {
    public void insert(DarenData darenData);

    public DarenData getByUserId(@Param("userIdd") Long userIdd);

}
