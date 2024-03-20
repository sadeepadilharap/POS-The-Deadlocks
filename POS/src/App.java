//Todo change Item manufacturer to more generic name like supplier

import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the POS system");
        System.out.print("Please login cashier : ");
        String cashier = scanner.nextLine();
        String cashierName;
        String branch;
        while (true) {
            if (cashier.equalsIgnoreCase("Nimal")) {
                cashierName = "Nimal";
                branch = "Moratuwa";
                break;
            } else if (cashier.equalsIgnoreCase("Kamal")) {
                cashierName = "Kamal";
                branch = "Moratuwa";
                break;
            } else if (cashier.equalsIgnoreCase("Sunil")) {
                cashierName = "Sunil";
                branch = "Dehiwala";
                break;
            } else {
                System.out.println("Invalid cashier name.");
                /*
                 * 
                 * exit the programme
                 * 
                 */
            }
        }
        System.out.println("Welcome " + cashierName + " !!");
        Store store = SaveManager.loadStore();
        CustomerList customers = SaveManager.loadCustomers();
        Customer customer = null;
        while (true) {
            System.out.println("Enter customer mobile number (enter 'I' for ignore) (enter 'R' for Register): ");
            String mobileNumber = scanner.nextLine();
            if (mobileNumber.equalsIgnoreCase("I")) {
                System.out.println("You are continue as a guest customer.");
                break;
            } else if (mobileNumber.equalsIgnoreCase("I")) {
                if (customers.isAvailable(mobileNumber)) {
                    customer = customers.get(mobileNumber);
                    System.out.println("Welcome back " + customer.getName());
                    break;
                } else {
                    while (true) {
                        System.out.println("Customer is not registerd or invalid mobile number.");
                        System.out.print("Does customer wants to enter mobile number again (yes/no) : ");
                        String reEnter = scanner.nextLine();
                        if (reEnter.equalsIgnoreCase("yes")) {
                            continue;
                        } else if (reEnter.equalsIgnoreCase("no")) {
                            while (true) {
                                System.out.println("Does customer wants to register (yes/no) : ");
                                String register = scanner.nextLine();

                                // New customer registration
                                if (register.equalsIgnoreCase("yes")) {
                                    System.out.print("Enter customer name : ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter customer address : ");
                                    String address = scanner.nextLine();
                                    customers.add(name, mobileNumber, address);
                                    SaveManager.saveCustomers(customers);
                                    break;
                                } else if (register.equalsIgnoreCase("no")) {
                                    System.out.println("You are continue as a guest customer.");
                                    break;
                                } else {
                                    System.out.println("Invalid input");
                                }
                            }
                            break;
                        } else {
                            System.out.println("Invalid input");
                        }

                    }

                }
                break;
            } else if (mobileNumber.equalsIgnoreCase("R")) {
                System.out.print("Enter customer mobile number : ");
                String mobileNumberNew = scanner.nextLine();
                System.out.print("Enter customer name : ");
                String name = scanner.nextLine();
                System.out.print("Enter customer address : ");
                String address = scanner.nextLine();
                customers.add(name, mobileNumberNew, address);
                SaveManager.saveCustomers(customers);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }

        while (true) {
            // check the customer wants a new bill or continue pending bill
            System.out.println("Do you want a new bill (yes/no) : ");
            String newBill = scanner.nextLine();
            BillCatalog billCatalog = SaveManager.loadBillCatalog();
            if (newBill.equalsIgnoreCase("yes")) {
                Bill bill = new Bill(cashierName, branch, customer == null ? "Guest" : customer.getName());
                billCatalog.add(bill);

                while (true) {
                    System.out.println("Enter item code (enter 'E' to end) : ");
                    String itemCode = scanner.nextLine();
                    if (itemCode.equalsIgnoreCase("E")) {
                        System.out.println("Billing process ended.");
                        break;
                    } else {
                        try {
                            double quantity = 1;
                            GlossaryItem item = store.get(itemCode);
                            System.out.print("Quantity or Weight(kg) : ");
                            quantity = scanner.nextDouble();
                            System.out.print("Discount percentage : ");
                            double discountPercentage = scanner.nextDouble();
                            double price = item.getPrice() * quantity * discountPercentage / 100;
                            bill.addItem(item, quantity, price, discountPercentage);
                        } catch (ItemCodeNotFound e) {
                            System.out.println("Item code not found. Please enter a valid item code.");
                        }
                    }
                }
                SaveManager.saveBillCatalog(billCatalog);
            } else if (newBill.equalsIgnoreCase("no")) {

                System.out.println("Do you want to continue a pending bill (yes/no) : ");

            } else {
                System.out.println("Invalid input");
            }

        }
    }

    //Printing the bill
    public static void printBill(Bill bill) {
        File file = new File("Bill.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            // =======Welcome to Deadlocks Store========
            writer.println("======= Welcome to Deadlocks Store ========\n\n");
            writer.println("Date and time: " + LocalDateTime.now());
            writer.println("Cashier: " + bill.getCashierName());
            writer.println("Branch: " + bill.getBranch());
            writer.println("Customer: " + bill.getCustomerName());
            writer.println("Items:");
            double TotalDiscount = 0;
            for (Object item : bill.getItemList()) {
                List<Object> details = (List<Object>) item;
                GlossaryItem groceryItem = (GlossaryItem) details.get(0);
                double quantity = (double) details.get(1);
                double price = (double) details.get(2);
                double discountPercentage = (double) details.get(3);
                writer.println("\t" + groceryItem.getItemName() + " : " + quantity + " x " + groceryItem.getPrice()
                        + "LKR - " + discountPercentage * 100 + "% = " + price);
                TotalDiscount += groceryItem.getPrice() * quantity * discountPercentage;
            }
            writer.println("Total discount: " + TotalDiscount);
            writer.println("Total price: " + bill.getTotalPrice());

            writer.println("======= Thank you for shopping with us! =======\n\n");

            // Print to terminal
            System.out.println("======= Welcome to Deadlocks Store ========\n\n");
            System.out.println("Date and time: " + LocalDateTime.now());
            System.out.println("Cashier: " + bill.getCashierName());
            System.out.println("Branch: " + bill.getBranch());
            System.out.println("Customer: " + bill.getCustomerName());
            System.out.println("Items:");
            TotalDiscount = 0;
            for (Object item : bill.getItemList()) {
                List<Object> details = (List<Object>) item;
                GlossaryItem groceryItem = (GlossaryItem) details.get(0);
                double quantity = (double) details.get(1);
                double price = (double) details.get(2);
                double discountPercentage = (double) details.get(3);
                System.out.println("\t" + groceryItem.getItemName() + " : " + quantity + " x " + groceryItem.getPrice()
                        + "LKR - " + discountPercentage * 100 + "% = " + price);
                TotalDiscount += groceryItem.getPrice() * quantity * discountPercentage;
            }
            System.out.println("Total discount: " + TotalDiscount);
            System.out.println("Total price: " + bill.getTotalPrice());

            System.out.println("======= Thank you for shopping with us! =======\n\n");

            System.out.println("Bill.txt created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
/*
 * public void addItem(GlossaryItem item, double quantity, double price, double
 * discountPercentage) {
 * List<Object> details = new ArrayList<>();
 * details.add(item);
 * details.add(quantity);
 * details.add(price);
 * details.add(discountPercentage);
 * itemList.add(details);
 * totalPrice += price * quantity;
 * }
 */
/*
 * Sure, here's a full description you can provide to the GitHub Copilot AI for
 * implementing the Point of Sale (POS) system for the supermarket:
 * 
 * Task: Develop a Java application for a Point of Sale (POS) system for the
 * Super-Saving supermarket chain with the following requirements:
 * 
 * 1. The POS system should allow the cashier to enter item codes to add grocery
 * items to the bill.
 * 2. For each entered item code, the system should fetch and display item
 * details like price, weight/size, manufacturing date, expiry date, and
 * manufacturer name. You can hardcode these item details instead of connecting
 * to an actual database.
 * 3. Each item can have a variable discount between 0-75%, which should be
 * applied to the item's price.
 * 4. The generated bill should contain the following information:
 * - Cashier's name
 * - Branch name
 * - Customer name (if a registered customer)
 * - List of items with unit price, quantity, discount percentage, and net price
 * for each item
 * - Total discount amount
 * - Total bill amount
 * - Date and time when the bill was printed
 * 5. The system should support handling pending bills using serialization. If a
 * customer needs to weigh some vegetables/fruits after the cashier has already
 * entered some items, the cashier should be able to keep that bill as pending,
 * serialize it, and serve other customers. The pending bill can be deserialized
 * later to continue processing.
 * 6. Create a custom exception called "ItemCodeNotFound" to handle situations
 * where the cashier enters an incorrect item code.
 * 7. Modify the `getItemDetails()` method in the `POS` class to throw the
 * `ItemCodeNotFound` exception when an incorrect item code is entered. The
 * method should then allow the cashier to re-enter the item code until a valid
 * code is provided.
 * 
 * The `getItemDetails()` method in the `POS` class is initially provided as:
 * 
 * ```java
 * class POS {
 * public GroceryItem getItemDetails() {
 * try {
 * InputStreamReader r = new InputStreamReader(System.in);
 * BufferedReader br = new BufferedReader(r);
 * item_code = br.readLine();
 * GroceryItem item = null;
 * // Fetch item details from the database
 * br.close();
 * r.close();
 * } catch (ItemCodeNotFound e) {}
 * return item;
 * }
 * }
 * ```
 * 
 * You need to modify this method to handle the `ItemCodeNotFound` exception and
 * allow re-entering the item code.
 * 
 * Additional requirements:
 * - Implement appropriate classes like `GroceryItem`, `Bill`, `Customer`, etc.,
 * with necessary attributes and methods.
 * - Add comments for code readability.
 * - Follow best practices for exception handling, object-oriented programming,
 * and Java coding conventions.
 * - The final code should be included in a PDF file named with your unique
 * identifier (e.g., 211234A.pdf) and uploaded to the provided link.
 * 
 * Note: This is a Turnitin assignment, and code plagiarism will be checked
 * automatically.
 * 
 * Let me know if you need any clarification or have additional requirements for
 * this task.
 */