package com.dao;

import com.model.SaveData20171018;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by honor on 2017/10/26.
 */
public interface SaveData20171018Dao {

    public List<SaveData20171018> findAll();

    public List<SaveData20171018> getLinkAs(String link);

    public List<SaveData20171018> findRankZero();

    public Integer getLinkCount(String link);

    public void update(SaveData20171018 data);

    public void insert(SaveData20171018 data);

    public SaveData20171018 getByLinkAndItem(@Param("dataLink") String dataLink, @Param("itemId")String itemId);
}
