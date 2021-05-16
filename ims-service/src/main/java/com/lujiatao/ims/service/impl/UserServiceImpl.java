package com.lujiatao.ims.service.impl;

import com.lujiatao.ims.api.UserService;
import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.service.repository.UserDAO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService(version = "1.0.0")
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean add(User user) {
        return userDAO.insert(user);
    }

    @Override
    public boolean modify(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteById(int id) {
        return userDAO.deleteById(id);
    }

    @Override
    public User getById(int id) {
        return userDAO.selectById(id);
    }

    @Override
    public List<User> search(String username) {
        return userDAO.selectByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userDAO.selectAll();
    }

}
