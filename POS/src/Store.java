import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private static Map<String, GroceryItem> itemsMap = new HashMap<>();

    public static void initializeItems() {
        itemsMap.put("123456", new GroceryItem("123456", "Bananas", 600, 1,
                LocalDate.of(2024, 3, 15), LocalDate.of(2024, 12, 31),
                "ABC Foods", 0.10));
        itemsMap.put("789012", new GroceryItem("789012", "Apples", 1500, 1,
                LocalDate.of(2024, 4, 10), LocalDate.of(2024, 12, 31),
                "XYZ Supermarket", 0.05));
        itemsMap.put("234567", new GroceryItem("234567", "Bread", 150, 700,
                LocalDate.of(2024, 3, 20), LocalDate.of(2024, 12, 31),
                "Fresh Mart", 0.15));
        itemsMap.put("345678", new GroceryItem("345678", "Milk", 200, 200,
                LocalDate.of(2024, 3, 25), LocalDate.of(2024, 12, 31),
                "Green Grocer", 0.0));
        itemsMap.put("456789", new GroceryItem("456789", "Eggs", 60, 1,
                LocalDate.of(2024, 3, 18), LocalDate.of(2024, 12, 31),
                "Farmers' Market", 0.20));
    }

    public static GroceryItem getItem(String itemCode) {
        return itemsMap.get(itemCode);
    }

    public static void addItem(String itemCode, String itemName, double price, double weightOrSize,
            LocalDate manufacturingDate, LocalDate expiryDate,
            String manufacturer, double discount) {
        GroceryItem newItem = new GroceryItem(itemCode, itemName, price, weightOrSize,
                manufacturingDate, expiryDate, manufacturer, discount);
        itemsMap.put(itemCode, newItem);
    }
    

}
