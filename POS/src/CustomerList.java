import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class CustomerList implements Serializable{
    Map<String, Customer> customerList;

    public CustomerList() {
        this.customerList = makeCustomerList();
    }

    // Making few dummy customers for testing
    private Map<String, Customer> makeCustomerList() {
        Map<String, Customer> customerList = new HashMap<>();
        customerList.put("1234567890", new Customer("John Doe", "1234567890", "123, Baker Street, London"));
        customerList.put("0987654321", new Customer("Jane Doe", "0987654321", "456, Oxford Street, London"));
        customerList.put("1122334455", new Customer("Alice", "1122334455", "789, Regent Street, London"));
        customerList.put("6677889900", new Customer("Bob", "6677889900", "101, Bond Street, London"));
        return customerList;
    }

    public boolean isAvailable(String mobileNu) {
        return customerList.containsKey(mobileNu);
    }

    public Customer get(String mobileNu) {
        return customerList.get(mobileNu);
    }

    public void add(String name, String mobileNu, String address) {
        customerList.put(mobileNu, new Customer(name, mobileNu, address));
    }

    public void remove(String mobileNu) {
        customerList.remove(mobileNu);
    }
}
