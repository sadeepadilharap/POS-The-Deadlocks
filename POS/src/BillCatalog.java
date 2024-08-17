import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;

public class BillCatalog implements Serializable{
    private Map<String, Bill> billList;

    public BillCatalog() {
        this.billList = new HashMap<String, Bill>();
    }

    // Add a new bill to the catalog incrementing the billId
    public String add(Bill bill) {
        String billId = String.valueOf(billList.size() + 1);
        billList.put(billId, bill);
        bill.setBillNu(billId);
        return billId;
    }

    public Boolean isAvailable(String billId) {
        return billList.containsKey(billId);
    }

    public Bill get(String billId) {
        return billList.get(billId);
    }
}
