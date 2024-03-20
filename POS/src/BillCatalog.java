import java.util.Map;
import java.util.HashMap;

public class BillCatalog {
    private Map<String, Bill> billList;

    public BillCatalog() {
        this.billList = new HashMap<String, Bill>();
    }

    // Add a new bill to the catalog incrementing the billId
    public String add(Bill bill) {
        String billId = String.valueOf(billList.size() + 1);
        billList.put(billId, bill);
        return billId;
    }

    public Boolean isAvailable(String billId) {
        return billList.containsKey(billId);
    }

    public Bill get(String billId) {
        return billList.get(billId);
    }
}
