package com.dao;

import com.model.InvitationGoodsOld;

import java.util.List;

/**
 * Created by honor on 2017/12/11.
 */
public interface InvitationGoodsOldDao {

   public List<InvitationGoodsOld> getByInvitationOld(Integer invitationId);
}
