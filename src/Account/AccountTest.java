package Account;

import Account.Account;

public class AccountTest {
    public static void main(String[] args) throws Exception{
        AccountDaoImpl accountDao=new AccountDaoImpl();
        String cardNo="6002";
        Account a=accountDao.select(cardNo);
        System.out.println( a.toString());
    }
}
