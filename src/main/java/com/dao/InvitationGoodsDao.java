package com.dao;

import com.model.InvitationGoods;


import java.util.List;

/**
 * Created by honor on 2017/12/11.
 */
public interface InvitationGoodsDao {
    public void insert(InvitationGoods invitationGoods);

    public List<InvitationGoods> getByInvitation(Integer invitationId);
}
