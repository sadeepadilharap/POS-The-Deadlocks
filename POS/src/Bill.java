import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {

    public boolean isComplete;
    private String cashierName;
    private String branch;
    private String customerName;
    public List<Object> itemList;
    private double totalPrice;
    private String billNu;
    

    public Bill(String cashierName, String branch, String customerName) {
        this.cashierName = cashierName;
        this.branch = branch;
        this.customerName = customerName;
        this.itemList = new ArrayList<>();
        this.totalPrice = 0.0;
        this.isComplete = false;
    }

    public void addItem(GlossaryItem item, double quantity, double price, double discountPercentage) {
        List<Object> details = new ArrayList<>();
        details.add(item);
        details.add(quantity);
        details.add(price);
        details.add(discountPercentage);
        itemList.add(details);
        totalPrice += price * quantity;
    }
   
  

    // Getters and Setters

    public String getCashierName() {
        return cashierName;
    }

    public String getBranch() {
        return branch;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Object> getItemList() {
        return itemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setComplete() {
        isComplete = true;
    }

    public void setBillNu(String billNu) {
        this.billNu = billNu;
    }

    public String getBillNu() {
        return billNu;
    }
}