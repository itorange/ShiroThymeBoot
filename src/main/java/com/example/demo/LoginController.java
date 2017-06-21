package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yangcheng on 2017/6/19.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(User user){
        if(StringUtils.isEmpty(user.getUsername())){
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        subject.login(token);
        //登录成功
        User u = (User)subject.getPrincipal();
        System.out.println("登录成功,登录名:"+u.getUsername()+"密码:"+u.getPassword());
        return "success";
    }
}
