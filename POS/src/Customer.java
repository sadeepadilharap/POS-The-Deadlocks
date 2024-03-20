//Store details of a buyer
import java.io.Serializable;

public class Customer implements Serializable{
    private String name;
    private String mobileNu;
    private String address;

    public Customer(String name, String mobileNu, String address) {
        this.name = name;
        this.mobileNu = mobileNu;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getMobileNu() {
        return mobileNu;
    }

    public String getAddress() {
        return address;
    }
}