package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.api.UserService;
import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.web.util.Paginationer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @DubboReference(version = "1.0.0")
    private UserService userService;
    private final Paginationer paginationer = new Paginationer();

    @GetMapping("/all")
    public BaseVO all() {
        List<User> list = userService.getAll();
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @GetMapping("/page")
    public BaseVO page(@RequestParam("username") String username, @RequestParam("targetPage") int targetPage) {
        List<User> list;
        if (username.equals("")) {
            list = userService.getAll();
        } else {
            list = userService.search(username);
        }
        Object[] objects = paginationer.getTargetDatas(list, targetPage);
        return new BaseVO(objects);
    }

    @GetMapping("/search")
    public BaseVO search(@RequestParam("username") String username) {
        List<User> list;
        if (username.equals("")) {
            list = userService.getAll();
        } else {
            list = userService.search(username);
        }
        Object[] objects = paginationer.getTargetDatas(list, 1);
        return new BaseVO(objects);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO add(@RequestBody User user) {
        int id = user.getId();
        User new_user = userService.getById(id);
        String username = user.getUsername();
        List<User> new_users = userService.search(username);
        if (new_user != null || new_users.size() != 0) {
            return new BaseVO(1, "该记录已存在！");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userService.add(user) ? new BaseVO() : new BaseVO(1, "未知异常！");
        }
    }

    @PutMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO modify(@RequestBody User user) {
        int id = user.getId();
        User new_user = userService.getById(id);
        if (new_user == null) {
            return new BaseVO(1, "该记录不存在！");
        } else if (id == 1) {
            return new BaseVO(1, "不能编辑admin用户！");
        } else {
            if (!user.getPassword().equals(new_user.getPassword())) {//修改了密码才加密
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            return userService.modify(user) ? new BaseVO() : new BaseVO(1, "未知异常！");
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole(\"ADMIN\")")
    public BaseVO delete(@RequestParam("id") int id) {
        User user = userService.getById(id);
        if (user == null) {
            return new BaseVO(1, "该记录不存在！");
        } else if (id == 1) {
            return new BaseVO(1, "不能删除admin用户！");
        } else {
            return userService.deleteById(id) ? new BaseVO() : new BaseVO(1, "未知异常！");
        }
    }

}
