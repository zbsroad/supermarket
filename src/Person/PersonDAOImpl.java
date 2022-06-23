package Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*id INT PRIMARY KEY AUTO_INCREMENT,
        `name` VARCHAR(20) NOT NULL,
        age INT NOT NULL,
        bornDate DATE,
        email VARCHAR(20),
        address VARCHAR(30)*/
public class PersonDAOImpl {
    //插入数据
    public int insert(Person person)  {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        connection=DBUtil.getConnection();
        String sql="INSERT INTO Person(name,age,bornDate,email,address) VALUES(?,?,?,?,?);";
        try {
            preparedStatement=connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(1,person.getName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setInt(2,person.getAge());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setDate(3,DateUtils.toSql(person.getBornDate()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(4,person.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStatement.setString(5,person.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int result= 0;
        try {
            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBUtil.closeALL(connection,preparedStatement,null);
        return result;
    }
    //修改数据
    public int update(Person person) throws Exception{
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        DBUtil a=new DBUtil();
        connection=a.getConnection();
        String sql="UPDATE Person set name=?,age=?,bornDate=?,email=?,address=? where id=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2,person.getAge());
        preparedStatement.setDate(3,DateUtils.toSql(person.getBornDate()));
        preparedStatement.setString(4,person.getEmail());
        preparedStatement.setString(5,person.getAddress());
        preparedStatement.setInt(6,person.getId());
        int result=preparedStatement.executeUpdate();
        return result;
}


//删除数据
    public int delete(int id){
     Connection connection=null;
     PreparedStatement preparedStatement=null;
     String sql="DELETE FROM Person WHERE id=?;";
     connection=DBUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int result=preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      finally {


        DBUtil.closeALL(connection,preparedStatement,null);}
        return 0;
    }
    public Person select(int id)
    {   Person person=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="SELECT * FROM Person WHERE id=?;";
        connection=DBUtil.getConnection();
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
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
            if(resultSet.next())
            {
                int sid=resultSet.getInt("id");
                String sname=resultSet.getString("name");
                int sage=resultSet.getInt("age");
                Date sbornDate=resultSet.getDate("bornDate");
                String semail=resultSet.getString("email");
                String saddress=resultSet.getString("address");
                person=new Person(sid,sname,sage,sbornDate,semail,saddress);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }
 public List<Person> selectAll(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        String sql="SELECT * FROM Person;";
        ResultSet resultSet=null;
        Person person=null;
        List<Person> personList=new ArrayList<>();
        connection=DBUtil.getConnection();
     try {
         preparedStatement=connection.prepareStatement(sql);
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
     try {
         resultSet=preparedStatement.executeQuery();
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }
     while(true)
        {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int sid= 0;
            try {
                sid = resultSet.getInt("id");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String sname= null;
            try {
                sname = resultSet.getString("name");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            int sage= 0;
            try {
                sage = resultSet.getInt("age");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Date sbornDate= null;
            try {
                sbornDate = resultSet.getDate("bornDate");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String semail= null;
            try {
                semail = resultSet.getString("email");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            String saddress= null;
            try {
                saddress = resultSet.getString("address");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            person=new Person(sid,sname,sage,sbornDate,semail,saddress);
            personList.add(person);
        }
     return personList;
 }

}
