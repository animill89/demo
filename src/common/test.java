package common;

import news.bean.User;

import java.util.List;

public class test {
    public static void main(String[] args){
        Userdao userdao= new Userdao();
        User user=new User();
//        user.setName("rose");
//        user.setPassword("123456");
////        user.setAge(18);
////        user.setPhone("1195694857");
////        user.setSex("1");
//        user.setRole("0");
//        user.setId(10);
//        userdao.queryUserForLogin(user);
        List<User> users=userdao.queryUserForLogin("lily","9","0");
        System.out.println("******"+users.size());
    }
}
