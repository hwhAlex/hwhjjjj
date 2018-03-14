package com.service.impl;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by honor on 2017/9/20.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    public void insert(User user){
        userDao.insert(user);
    }

    public User getById(Integer id){
        User user = userDao.getById(id);
        return user;
    }
}
