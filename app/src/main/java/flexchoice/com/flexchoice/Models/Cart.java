package flexchoice.com.flexchoice.Models;

public class Cart
{
    private String pname,  price, image,  pid;

    public Cart() {
    }

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

    public Cart(String pname, String price, String image, String pid) {

        this.pname = pname;
        this.price = price;
        this.image = image;
        this.pid = pid;
    }
}
