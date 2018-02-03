package com.heima.service.impl;

import com.heima.repository.UserRepository;
import com.heima.domain.User2;
import com.heima.mapper.UserMapper;
import com.heima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * xuan
 * 2018/2/3
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User2 user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUser(User2 user) {

    }

    @Override
    public void selectUserById() {

    }

    @Override
    public List<User2> findAll() {
        return userRepository.findAll();
    }
}
