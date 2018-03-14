package com.dao;

import com.model.AllContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by honor on 2018/1/31.
 */
public interface AllContentDao {
    public List<AllContent> getNoHandle(Integer id);

    public void updateDone(@Param("idList") List idList);
}
