package Account;

public class Account {
    private String cardNo;
    private String password;
    private String name;
    private double banlance;

    public Account() {
    }

    public Account(String cardNo, String password, String name, double banlance) {
        this.cardNo = cardNo;
        this.password = password;
        this.name = name;
        this.banlance = banlance;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBanlance() {
        return banlance;
    }

    public void setBanlance(double banlance) {
        this.banlance = banlance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNo='" + cardNo + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", banlance=" + banlance +
                '}';
    }
}
