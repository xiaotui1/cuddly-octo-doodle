package com.heima.service;

import com.heima.domain.User2;

import java.util.List;

/**
 * xuan
 * 2018/2/3
 */
public interface UserService {
    public void insertUser(User2 user);
    public void deleteUser(Integer id);
    public void updateUser(User2 user);
    public void selectUserById();
    public List<User2> findAll();
}
