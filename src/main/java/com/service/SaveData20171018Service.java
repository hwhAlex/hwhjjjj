package com.service;

import com.model.DataLink;
import com.model.SaveData20171018;
import com.model.SaveData20171018_1;
import com.model.XiaoHua;

import java.util.List;
import java.util.Map;

/**
 * Created by honor on 2017/10/26.
 */
public interface SaveData20171018Service {
    public void setRank();
    public void addLink();
    public List<SaveData20171018> getAll();
    public List<DataLink> getLinkAll();
    public Map saveData(SaveData20171018 data);

    public Map saveData1(SaveData20171018_1 data);

    public void addXiaoHua(XiaoHua xiaoHua);
}
