package flexchoice.com.flexchoice.Models;

public class Admin {
    public String  phone, password;

    public Admin() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String phone, String password) {

        this.phone = phone;
        this.password = password;
    }
}
