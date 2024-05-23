package com.pjb.servlet;

import common.Userdao;
import news.bean.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RejestServlet", urlPatterns = "/servlet/RejestServlet")
public class RejestServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //将字符修改为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取前段数据
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String sex = request.getParameter("sex");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        Userdao userdao= new Userdao();
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setAge(age);
        user.setPhone(phone);
        user.setSex(sex);
        user.setRole(role);
//        user.setId();
        userdao.addUser(user);
        request.getRequestDispatcher("../Enroll/view.jsp").forward(request, response);
    }
}