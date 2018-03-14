package com.service.impl;

import com.dao.AssignmentDao;
import com.model.Assignment;
import com.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by honor on 2017/11/21.
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentDao assignmentDao;


    @Override
    public Integer addAssignment(Assignment assignment) {
        Integer assignmentId = assignment.getAssignmentId();
        Integer count = assignmentDao.countAssignmentId(assignmentId);
        if(count > 0){
            return 0;
        }else {
            assignmentDao.insert(assignment);
            return assignment.getId();
        }
    }
}
