package com.zzt.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Admin;
import service.AdminService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@Controller
public class LoginController {


    @Reference
    AdminService adminService;

    //    到登陆界面
    @RequestMapping("/")
    public String toLogin() {
        return "jsp-login/login.jsp";
    }

    //    实现登陆功能验证 并跳转到主界面
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        System.out.println("接收到username：" + username);
        String password = request.getParameter("password");
        System.out.println("接收到password：" + password);
        String isRemeber = request.getParameter("rememberMe");
        System.out.println(isRemeber);
        Admin admin = adminService.getAdmin(username, password);
        if (admin != null) {
            Cookie cookie = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            if (isRemeber != null) {
                cookie2.setMaxAge(60 * 60);
                cookie.setMaxAge(60 * 60);
            } else {
                cookie2.setMaxAge(0);
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
            response.addCookie(cookie2);


            HttpSession session = request.getSession();
            session.setAttribute("currentAdmin", admin);
            return "jsp-login/main_user_login_logout.jsp";
        }
        return "jsp-login/login.jsp";
    }

    //    跳转到主界面上的用户条
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession httpSession) {
        httpSession.removeAttribute("currentAdmin");
        return "redirect:jsp-login/login.jsp";
    }

    //    跳转到theme选择的jsp上
    @RequestMapping("/xunhuan")
    public String theme(){
        return "jsp-login/jsp";
    }
}
