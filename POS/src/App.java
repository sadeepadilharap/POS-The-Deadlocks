import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }


    public static void printBill(Bill bill) {
        double  TotalDiscount = 0;
        System.out.println("Cashier: " + bill.getCashierName());
        System.out.println("Branch: " + bill.getBranch());
        System.out.println("Customer: " + bill.getCustomerName());
        System.out.println("Items:");
        for (Object item : bill.getItemList()) {
            List<Object> details = (List<Object>) item;
            GlossaryItem groceryItem = (GlossaryItem) details.get(0);
            double quantity = (double) details.get(1);
            double price = (double) details.get(2);
            double discountPercentage = (double) details.get(3);
            System.out.println(groceryItem.getItemName() + " - " + quantity + " x " + groceryItem.getPrice() + " - " + discountPercentage*100 + "% = " + price);
            TotalDiscount += groceryItem.getPrice() * quantity * discountPercentage;
        }
        System.out.println("Total discount: " + TotalDiscount);
        System.out.println("Total price: " + bill.getTotalPrice());
    }
}

/*    public void addItem(GlossaryItem item, double quantity, double price, double discountPercentage) {
        List<Object> details = new ArrayList<>();
        details.add(item);
        details.add(quantity);
        details.add(price);
        details.add(discountPercentage);
        itemList.add(details);
        totalPrice += price * quantity;
    } */

/*
 * Sure, here's a full description you can provide to the GitHub Copilot AI for implementing the Point of Sale (POS) system for the supermarket:

Task: Develop a Java application for a Point of Sale (POS) system for the Super-Saving supermarket chain with the following requirements:

1. The POS system should allow the cashier to enter item codes to add grocery items to the bill.
2. For each entered item code, the system should fetch and display item details like price, weight/size, manufacturing date, expiry date, and manufacturer name. You can hardcode these item details instead of connecting to an actual database.
3. Each item can have a variable discount between 0-75%, which should be applied to the item's price.
4. The generated bill should contain the following information:
   - Cashier's name
   - Branch name
   - Customer name (if a registered customer)
   - List of items with unit price, quantity, discount percentage, and net price for each item
   - Total discount amount
   - Total bill amount
   - Date and time when the bill was printed
5. The system should support handling pending bills using serialization. If a customer needs to weigh some vegetables/fruits after the cashier has already entered some items, the cashier should be able to keep that bill as pending, serialize it, and serve other customers. The pending bill can be deserialized later to continue processing.
6. Create a custom exception called "ItemCodeNotFound" to handle situations where the cashier enters an incorrect item code.
7. Modify the `getItemDetails()` method in the `POS` class to throw the `ItemCodeNotFound` exception when an incorrect item code is entered. The method should then allow the cashier to re-enter the item code until a valid code is provided.

The `getItemDetails()` method in the `POS` class is initially provided as:

```java
class POS {
    public GroceryItem getItemDetails() {
        try {
            InputStreamReader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
            item_code = br.readLine();
            GroceryItem item = null;
            // Fetch item details from the database
            br.close();
            r.close();
        } catch (ItemCodeNotFound e) {}
        return item;
    }
}
```

You need to modify this method to handle the `ItemCodeNotFound` exception and allow re-entering the item code.

Additional requirements:
- Implement appropriate classes like `GroceryItem`, `Bill`, `Customer`, etc., with necessary attributes and methods.
- Add comments for code readability.
- Follow best practices for exception handling, object-oriented programming, and Java coding conventions.
- The final code should be included in a PDF file named with your unique identifier (e.g., 211234A.pdf) and uploaded to the provided link.

Note: This is a Turnitin assignment, and code plagiarism will be checked automatically.

Let me know if you need any clarification or have additional requirements for this task.
 */