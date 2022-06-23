package Account;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static  final Properties PROPERTIES=new Properties(); //存储配置文件的map
    static{
    InputStream is=DBUtil.class.getResourceAsStream("/cb.properties");
        try {
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(PROPERTIES.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static  Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection(PROPERTIES.getProperty("url"),PROPERTIES.getProperty("user"),PROPERTIES.getProperty("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public static void closeALL(Connection connection, Statement statement, ResultSet resultSet){
        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}