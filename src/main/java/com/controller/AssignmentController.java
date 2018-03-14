package com.controller;

import com.model.Assignment;
import com.param.DarenParam;
import com.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by honor on 2017/11/21.
 */
@Controller
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @RequestMapping(value = "/addAssignment" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Integer addAssignment(Assignment assignment) throws Exception{
        Integer id =   assignmentService.addAssignment(assignment);
        return id;
    }
}
