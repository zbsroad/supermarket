package Account;

import Account.Account;

public class AccountService {
    public void transfer(String cardNo,String password,double acounnt,String bycardNo)
    {   AccountDaoImpl accountDao=new AccountDaoImpl();

        //1.判断用户是否存在即账号密码是否正确
        Account a=accountDao.select(cardNo);
        if(a!=null&&a.getPassword()==password)
        {
            System.out.println();
        }
    }
}
