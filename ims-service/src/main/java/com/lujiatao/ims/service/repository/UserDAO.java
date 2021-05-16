package com.lujiatao.ims.service.repository;

import com.lujiatao.ims.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    boolean insert(User user);

    boolean update(User user);

    boolean deleteById(int id);

    User selectById(int id);

    List<User> selectByUsername(String username);

    List<User> selectAll();

}
