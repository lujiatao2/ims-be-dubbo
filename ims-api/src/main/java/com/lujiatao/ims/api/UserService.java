package com.lujiatao.ims.api;

import com.lujiatao.ims.common.entity.User;

import java.util.List;

public interface UserService {

    boolean add(User user);

    boolean modify(User user);

    boolean deleteById(int id);

    User getById(int id);

    List<User> search(String username);

    List<User> getAll();

}
