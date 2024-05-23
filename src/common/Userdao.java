package common;

import news.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 增加
 * */
public class Userdao {
    public boolean addUser(User user) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/news";
            String username="root";
            String pass="123456";
            Connection conn = DriverManager.getConnection(url,username,pass);
            String sql="insert into user(name,password,age,phone,sex,role) values(?,?,?,?,?,?)";
            PreparedStatement prest= conn.prepareStatement(sql);
            prest.setString(1,user.getName());
            prest.setString(2,user.getPassword());
            prest.setInt(3,user.getAge());
            prest.setString(4,user.getPhone());
            prest.setString(5,user.getSex());
            prest.setString(6,user.getRole());
//            prest.setString(1,user.getName());
            //执行SQL
            int result = prest.executeUpdate();
            //关闭连接
            prest.close();
            conn.close();
            //返回数据库执行结果
            if(result>0){
                return true;
            }else {
                return false;
            }
        }catch (ClassNotFoundException e){
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除数据
     * */

    public boolean deleteUser(User user) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/news";
            String username="root";
            String pass="123456";
            Connection conn = DriverManager.getConnection(url,username,pass);
            String sql="DELETE FROM user WHERE name = ?";
            PreparedStatement prest= conn.prepareStatement(sql);
            prest.setString(1,user.getName());
//            prest.setString(1,user.getName());
            int result = prest.executeUpdate();
            prest.close();
            conn.close();
            if(result>0){
                return true;
            }else {
                return false;
            }
        }catch (ClassNotFoundException e){
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }
/*
* 修改
* */

    public boolean updateeUser(User user) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3306/news";
            String username="root";
            String pass="123456";
            Connection conn = DriverManager.getConnection(url,username,pass);
            String sql="update user set name=?,password=?,age=?,phone=?,sex=?,role=? where id=?";
            PreparedStatement prest= conn.prepareStatement(sql);
            prest.setString(1,user.getName());
            prest.setString(2,user.getPassword());
            prest.setInt(3,user.getAge());
            prest.setString(4,user.getPhone());
            prest.setString(5,user.getSex());
            prest.setString(6,user.getRole());
            prest.setInt(7,user.getId());
//            prest.setString(1,user.getName());
            int result = prest.executeUpdate();
            prest.close();
            conn.close();
            if(result>0){
                return true;
            }else {
                return false;
            }
        }catch (ClassNotFoundException e){
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return false;
    }


    /*
    * 登录查询
    * */
    public List<User> queryUser(String name){
        //准备好集合数据，存放用户对象的信息
        List<User> users = new ArrayList<User>();
        //第1步：加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //第2步:建立数据库的链接
            String url="jdbc:mysql://localhost:3306/news";//数据库连接地址
            String u="root";//数据库登录用户名
            String pass="123456";//数据库登录密码
            Connection conn= DriverManager.getConnection(url, u, pass);
            //第3步：创建PreparedStatement对象

            String sql="select * from user where 1=1 ";
            if(name!=null&&name.length()>0) {
                sql = sql + " and name=?";
            }
            PreparedStatement prest = conn.prepareStatement(sql);
            if(name!=null&&name.length()>0) {
                prest.setString(1, name);
            }

            //第4步：执行sql
            ResultSet rs = prest.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setAge(rs.getInt("age"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setRole(rs.getString("sex"));
                users.add(user);
            }
            //第5步：关闭链接，释放资源
            prest.close();
            conn.close();
            return users;
        }catch(ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败");
            e.printStackTrace();
        }catch(SQLException e) {
            System.out.println("链接数据库失败");
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 检索用户信息是否合法- 登录
     * @param name
     * @param password
     * @param role
     * @return
     */
    public List<User> queryUserForLogin(String name,String password,String role){
        //准备好集合数据，存放用户对象的信息
        List<User> users = new ArrayList<User>();
        //第1步：加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //第2步:建立数据库的链接
            String url="jdbc:mysql://localhost:3306/news";//数据库连接地址
            String u="root";//数据库登录用户名
            String pass="123456";//数据库登录密码
            Connection conn= DriverManager.getConnection(url, u, pass);
            //第3步：创建PreparedStatement对象
            String sql="select * from user where name=? and password=? and role=? ";

            PreparedStatement prest = conn.prepareStatement(sql);
            prest.setString(1, name);
            prest.setString(2, password);
            prest.setString(3, role);

            //第4步：执行sql
            ResultSet rs = prest.executeQuery();

//			Statement st = conn.createStatement();
//			String sql = "select * from user where name='"+name+"' and password='"+password+"' and role='"+role+"'";
//			System.out.println(sql);
//			ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setAge(rs.getInt("age"));
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setRole(rs.getString("sex"));
                users.add(user);
            }
            //第5步：关闭链接，释放资源
            prest.close();
//			st.close();
            conn.close();
            return users;
        }catch(ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败");
            e.printStackTrace();
        }catch(SQLException e) {
            System.out.println("链接数据库失败");
            e.printStackTrace();
        }
        return users;
    }


}

//@Override