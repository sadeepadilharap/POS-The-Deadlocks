import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.io.Serializable;

public class Store implements Serializable{
    private Map<String, GlossaryItem> glossary;

    public Store() {
        this.glossary = makeGlossary();
    }

    //Make some dummy items for testing purposes
    private Map<String, GlossaryItem> makeGlossary() {
        // Hardcoded item details
        Map<String, GlossaryItem> glossary = new HashMap<>();
        glossary.put("001", new GlossaryItem("001", "Apple", 2.5, 1.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Apple Inc.", 5));
        glossary.put("002", new GlossaryItem("002", "Banana", 1.5, 1.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Banana Inc.", 10));
        glossary.put("003", new GlossaryItem("003", "Orange", 3.0, 1.0, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Orange Inc.", 15));

        //Some more dry items like milk powder etc.
        glossary.put("004", new GlossaryItem("004", "Milk Powder", 5.0, 0.5, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Milk Inc.", 5));
        glossary.put("005", new GlossaryItem("005", "Sugar", 1.5, 1.0, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Sugar Inc.", 10));
        glossary.put("006", new GlossaryItem("006", "Salt", 3.0, 0.6, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Salt Inc.", 15));

        //Items like soap shampoo
        glossary.put("007", new GlossaryItem("007", "Soap", 5.0, 0.5, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Soap Inc.", 5));
        glossary.put("008", new GlossaryItem("008", "Shampoo", 1.5, 0.3, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Shampoo Inc.", 10));
        glossary.put("009", new GlossaryItem("009", "Toothpaste", 3.0, 0.6, LocalDate.of(2022, 1, 1), LocalDate.of(2023, 1, 10), "Toothpaste Inc.", 15));

        //Items like bread, butter, cheese
        glossary.put("010", new GlossaryItem("010", "Bread", 5.0, 0.5, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Bread Inc.", 5));
        glossary.put("011", new GlossaryItem("011", "Butter", 1.5, 0.3, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Butter Inc.", 10));
        glossary.put("012", new GlossaryItem("012", "Cheese", 3.0, 0.6, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Cheese Inc.", 15));

        //Items like Medicines paracetamol, panadol , c vitamin
        glossary.put("013", new GlossaryItem("013", "Paracetamol", 5.0, 0.5, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Paracetamol Inc.", 5));
        glossary.put("014", new GlossaryItem("014", "Panadol", 1.5, 0.3, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "Panadol Inc.", 10));
        glossary.put("015", new GlossaryItem("015", "C Vitamin", 3.0, 0.6, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10), "C Vitamin Inc.", 15));

        return glossary;
    }

    public GlossaryItem get(String itemCode) throws ItemCodeNotFound {
        if (!glossary.containsKey(itemCode)) {
            throw new ItemCodeNotFound("Item code not found");
        }
        return glossary.get(itemCode);
    }

    //Add item to glossary generating the Item code automatically by incrementing the last item code and return new Item Code and generate the glossary item inside the method
    public String add(String itemName, double price, double weight, LocalDate manufacturingDate, LocalDate expiryDate, String manufacturerName, double discount) {
        String itemCode = String.format("%03d", glossary.size() + 1);
        glossary.put(itemCode, new GlossaryItem(itemCode, itemName, price, weight, manufacturingDate, expiryDate, manufacturerName, discount));
        return itemCode;
    }

    public void remove(String itemCode) {
        glossary.remove(itemCode);
    }
}

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