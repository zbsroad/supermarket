package Person;

import java.util.Date;

public class PersonTest {
    public static void main(String[] args) {
        PersonDAOImpl personDAO = new PersonDAOImpl();
//        Person aPerson=new Person("张斌",20,null,"1941041786@qq.com","山东省邹平市");
//        Person bPerson=new Person("王浩飞",20,null,"121312321321@qq.com","山东省邹平市");
//        personDAO.insert(aPerson);
//        personDAO.insert(bPerson);
//      System.out.println(personDAO.selectAll());
        Person cPerson=new Person("张飞",19,new Date(),"zhangfei@163.com","山东菏泽曹县");
        personDAO.insert(cPerson);
        String str="2001-07-29";
        Person dPerson=new Person("张前",19,DateUtils.toUtil(str),"zhangfei@163.com","山东菏泽曹县");
        personDAO.insert(dPerson);
    }
}