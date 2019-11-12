package flexchoice.com.flexchoice.Models;

public class Order {

    private String pname;
    private String price;
    private String image;
    private String pid;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Order(String pname, String price, String image, String pid, String state) {

        this.pname = pname;
        this.price = price;
        this.image = image;
        this.pid = pid;
        this.state = state;
    }

    private String state;

}
