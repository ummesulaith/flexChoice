package flexchoice.com.flexchoice.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    public final String name;
    String prod_title;

    public Product(String name) {
        this.name=name;
    }

    protected Product(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
    }

    public void getProd_title(String prod_title) {
        this.prod_title = prod_title;
    }


}
