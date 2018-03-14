package com.dao;
import com.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by honor on 2017/9/20.
 */
public interface UserDao{

    public void insert(User user);

    public User getById(Integer id);
}
