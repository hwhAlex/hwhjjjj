package com.dao;

import com.model.InvitationOld;

import java.util.List;

/**
 * Created by honor on 2017/12/11.
 */
public interface InvitationOldDao {
    public List<InvitationOld> getNoHandle();
    public void update(Integer id);

}
