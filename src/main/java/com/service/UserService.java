package com.service;


import com.model.User;

/**
 * Created by honor on 2017/9/20.
 */
public interface UserService {

    public void insert(User user);

    public User getById(Integer id);
}
