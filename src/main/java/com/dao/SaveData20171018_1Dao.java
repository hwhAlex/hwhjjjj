package com.dao;

import com.model.SaveData20171018;
import com.model.SaveData20171018_1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by honor on 2017/11/22.
 */
public interface SaveData20171018_1Dao {

    public List<SaveData20171018_1> findAll();

    public List<SaveData20171018_1> getLinkAs(String link);

    public List<SaveData20171018_1> findRankZero();

    public Integer getLinkCount(String link);

    public void update(SaveData20171018_1 data);

    public void insert(SaveData20171018_1 data);

    public SaveData20171018_1 getByLinkAndItem(@Param("dataLink") String dataLink, @Param("itemId")String itemId);
}
