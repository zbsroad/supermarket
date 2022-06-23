package com.test;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/db_test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false";
        String user="root";
        String password="123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection(url,user,password);
        Scanner in=new Scanner(System.in);
        System.out.println("请输入用户名");
        String a=in.next();
        System.out.println("请输入密码");
        String b=in.next();
        PreparedStatement preparedStatement=conn.prepareStatement("select * from Users where username=? and userpassword=?");
        preparedStatement.setString(1,a);
        preparedStatement.setString(2,b);
        ResultSet resultSet=preparedStatement.executeQuery();

        if(resultSet.next())
        {
            System.out.println("登陆成功");
        }
        else{
            System.out.println("登陆失败");
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();


    }
}
