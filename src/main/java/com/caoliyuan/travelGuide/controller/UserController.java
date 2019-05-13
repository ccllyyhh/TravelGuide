package com.caoliyuan.travelGuide.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.caoliyuan.travelGuide.domain.UserModel;
import com.caoliyuan.travelGuide.service.UserService;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/temple",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String temple(@RequestParam("arg1") String arg1,
                        @RequestParam("arg2") String arg2){

        JSONObject rsp = new JSONObject();
        //TODO 你的业务逻辑
        rsp.put("success",true);
        rsp.put("info","登录成功");
        return rsp.toString();
    }

    @RequestMapping(value = "/user/login",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){

        JSONObject rsp = new JSONObject();

        UserModel user = userService.findUserByUsername(username);
        if(user!=null&&user.getPASSWORD().equals(password)){
            //登录成功
            rsp.put("success",true);
            rsp.put("info","登录成功");
        }else{
            //登陆失败
            rsp.put("success",false);
            rsp.put("info","用户名或密码错误");
        }
        return rsp.toString();
    }

    @RequestMapping(value = "/user/reg",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone){

        JSONObject rsp = new JSONObject();

        UserModel user = userService.findUserByUsername(username);
        if(user!=null){
            rsp.put("success",false);
            rsp.put("info","用户名已存在");
        }else if(phone.length()!=11){
            rsp.put("success",false);
            rsp.put("info","手机号长度有误");
        }else if (password.length()<6||password.length()>20){
            rsp.put("success",false);
            rsp.put("info","密码长度超过限制");
        }else {
            boolean s = userService.addUser(new UserModel(username,phone,password));
            if(s) {
                rsp.put("success", true);
                rsp.put("info", "注册成功");
            }else{
                rsp.put("success",false);
                rsp.put("info","注册失败");
            }
        }



        return rsp.toString();
    }

    @RequestMapping(value = "/user/update",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String update(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone){
        JSONObject rsp = new JSONObject();
        UserModel user = userService.findUserByUsername(username);

        rsp.put("success",true);
        rsp.put("info","登录成功");
        return rsp.toString();
    }

}