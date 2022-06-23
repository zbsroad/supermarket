package Account;

import Account.Account;
import Person.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl {
    public int insert(Account account) throws Exception
    {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="insert into Account VALUES(?,?,?,?)";
        connection= DBUtil.getConnection();
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,account.getCardNo());
        preparedStatement.setString(2,account.getPassword());
        preparedStatement.setString(3,account.getName());
        preparedStatement.setDouble(4,account.getBanlance());
       int result=preparedStatement.executeUpdate();
       return result;
    }
    public int update(Account account){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="UPDATE Account SET name=?,password=?,balance=? WHERE cardNo=?";
        connection=DBUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(1,account.getName());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setDouble(3,account.getBanlance());
            preparedStatement.setString(4,account.getCardNo());
            int result=preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }
    public Account select(String cardNo){
        Account account=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="select * from Account WHERE cardNo=?";
        connection=DBUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(1,cardNo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet resultSet= null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(resultSet.next()){
                String rcardNo=resultSet.getString("cardNo");
                String rname=resultSet.getString("name");
                String rpassword=resultSet.getString("password");
                Double rbanlance=resultSet.getDouble("balance");
                account=new Account(rcardNo,rname,rpassword,rbanlance);
                return account;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
