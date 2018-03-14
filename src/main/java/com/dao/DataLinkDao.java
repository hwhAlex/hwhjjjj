package com.dao;

import com.model.DataLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by honor on 2017/10/26.
 */
public interface DataLinkDao {

   public void insert(DataLink dataLink);

   public DataLink getByLink(String dataLink);

   public List<DataLink> getAll();
}
