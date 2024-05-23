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

@WebServlet(name = "LoginServlet", urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取前端输入信息
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        System.out.println(role);

        //调用对象，登录查询对象
        Userdao userdao = new Userdao();
        List<User> users = userdao.queryUserForLogin(name, password, role);

//        System.out.println("-------"+users.size());//测试users.size的长度
        //流程控制
        if (users.size() > 0)
        {
            System.out.println("进入成功");
            User user = users.get(0);
            HttpSession session = request.getSession();//创建session对象，存在30分钟
            session.setAttribute("user", user);//存在session对象中去
            if(role.equals("0")){
                request.getRequestDispatcher("../common/user_main.jsp").forward(request, response);//跳转界面
            }else {
                request.getRequestDispatcher("../common/admin_main.jsp").forward(request,response);
            }
        }
        else
        {
            response.sendRedirect("/login.jsp");
        }
    }
}