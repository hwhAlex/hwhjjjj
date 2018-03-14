package com.dao;

import com.model.Assignment;

/**
 * Created by honor on 2017/11/21.
 */
public interface AssignmentDao {
    public void insert(Assignment assignment);
    public Integer countAssignmentId(Integer assignmentId);
}
