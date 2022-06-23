package com.test;

import java.sql.Connection;

public class DBUtilTest {
    public static void main(String[] args) {
        Connection connection=DBUtil.getConnection();
        System.out.println(connection);
    }
}
