package com.lujiatao.ims.web.controller;

import com.lujiatao.ims.common.model.BaseVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class CommonController {

    @GetMapping("/logined-user")
    public BaseVO loginedUser() {
        Map<String, String> user = new HashMap<>();
        user.put("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseVO(user);
    }

    @GetMapping("/access-denied")
    public BaseVO accessDenied() {
        return new BaseVO(1, "没有权限！");
    }

}
