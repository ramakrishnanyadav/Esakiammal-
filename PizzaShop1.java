import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class PizzaShop1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Vector<String> pizzaMenu = new Vector<>();
    private static final Vector<Double> pizzaPrices = new Vector<>();
    private static final Vector<String> orderedPizzas = new Vector<>();
    private static final Vector<Integer> quantities = new Vector<>();
    private static final Vector<Double> orderPrices = new Vector<>();

    public static void main(String[] args) {
        initializeMenu();
        String customerName = getCustomerName();
        String phoneNumber = getPhoneNumber();
        double totalBill = runShop();
        printOrderSummary(); // Print summary before final bill
        printBill(customerName, phoneNumber, totalBill);
    }

    // Initialize the pizza menu with names and prices
    private static void initializeMenu() {
        pizzaMenu.add("Margherita Pizza");
        pizzaMenu.add("Pepperoni Pizza");
        pizzaMenu.add("Veggie Pizza");
        pizzaMenu.add("BBQ Chicken Pizza");
        pizzaMenu.add("Paneer Pizza");
        pizzaMenu.add("Four Cheese Pizza");
        pizzaMenu.add("Chicken Lovers Pizza");
        pizzaMenu.add("Chicken Dinner Pizza");
        pizzaMenu.add("Corn Pizza");
        pizzaMenu.add("Onion Pizza");
        pizzaMenu.add("Chicken Alfredo Pizza");
        pizzaMenu.add("Capsicum Pizza");
        pizzaMenu.add("Sausage and Peppers Pizza");
        pizzaMenu.add("Mushroom and Onion Pizza");
        pizzaMenu.add("Crab Pizza");

        // Prices corresponding to the pizzas
        pizzaPrices.add(100.00); 
        pizzaPrices.add(150.00); 
        pizzaPrices.add(80.00); 
        pizzaPrices.add(105.00); 
        pizzaPrices.add(110.00); 
        pizzaPrices.add(120.00); 
        pizzaPrices.add(130.00); 
        pizzaPrices.add(140.00);
        pizzaPrices.add(150.00); 
        pizzaPrices.add(160.00); 
        pizzaPrices.add(170.00); 
        pizzaPrices.add(180.00); 
        pizzaPrices.add(190.00); 
        pizzaPrices.add(50.00); 
        pizzaPrices.add(300.00); 
    }

    // Get the customer's name
    private static String getCustomerName() {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }

    // Get the customer's phone number and validate for 10 digits
    private static String getPhoneNumber() {
        String phoneNumber = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your phone number (10 digits): ");
            phoneNumber = scanner.nextLine();

            // Check if the phone number is exactly 10 digits
            if (phoneNumber.matches("\\d{10}")) {
                validInput = true;
            } else {
                System.out.println("Invalid phone number. Please enter a valid 10-digit phone number.");
            }
        }
        return phoneNumber;
    }

    // Main loop to run the pizza shop
    private static double runShop() {
        double totalBill = 0;

        // Show menu only once
        displayMenu();

        while (true) {
            int choice = getUserChoice();

            if (choice == pizzaMenu.size() + 1) {
                System.out.println("Exiting the program. Thank you for visiting!");
                break;
            }

            if (choice >= 1 && choice <= pizzaMenu.size()) {
                int quantity = getPizzaQuantity();
                double orderTotal = processOrder(choice, quantity);
                totalBill += orderTotal;
            } else {
                System.out.println("Invalid number. Please select a number between 1 and " + pizzaMenu.size() + ".");
            }
        }
        return totalBill;
    }

    // Display the pizza menu with prices
    private static void displayMenu() {
        System.out.println("\nWelcome to the Pizza Shop!");
        for (int i = 0; i < pizzaMenu.size(); i++) {
            System.out.println((i + 1) + ". " + pizzaMenu.get(i) + " - Rs." + String.format("%.2f", pizzaPrices.get(i)));
        }
        System.out.println((pizzaMenu.size() + 1) + ". Exit");
    }

    // Get and validate user choice
    private static int getUserChoice() {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the number of the pizza you want to order (or " + (pizzaMenu.size() + 1) + " to exit): ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice >= 1 && choice <= pizzaMenu.size() + 1) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please select a number between 1 and " + (pizzaMenu.size() + 1) + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    // Get the quantity for the selected pizza
    private static int getPizzaQuantity() {
        int quantity = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter the quantity of the pizza: ");
            try {
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (quantity > 0) {
                    validInput = true;
                } else {
                    System.out.println("Quantity must be greater than 0. Please enter a valid quantity.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return quantity;
    }

    // Process the user's pizza order and return the price
    private static double processOrder(int choice, int quantity) {
        String pizza = pizzaMenu.get(choice - 1);
        double price = pizzaPrices.get(choice - 1);
        double orderTotal = price * quantity;

        // Store order details
        orderedPizzas.add(pizza);
        quantities.add(quantity);
        orderPrices.add(orderTotal);

        System.out.println("You ordered " + quantity + " " + pizza + "(s) for Rs." + String.format("%.2f", orderTotal) + ".");
        return orderTotal;
    }

    // Print the final bill with a 10% discount
    private static void printBill(String customerName, String phoneNumber, double totalBill) {
        double discount = 0.10; // 10% discount
        double discountedBill = totalBill - (totalBill * discount);

        System.out.println("\n--- Final Bill ---");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Total Bill: Rs." + String.format("%.2f", totalBill));
        System.out.println("Discount (10%): -Rs." + String.format("%.2f", totalBill * discount));
        System.out.println("Total After Discount: Rs." + String.format("%.2f", discountedBill));
        System.out.println("\nThank you for your order! Your pizza will be ready soon.");
    }    

    // Print the order summary
    private static void printOrderSummary() {
        System.out.println("\n--- Order List ---");
        for (int i = 0; i < orderedPizzas.size(); i++) {
            String pizza = orderedPizzas.get(i);
            int quantity = quantities.get(i);
            double price = orderPrices.get(i);
            System.out.println(pizza + ": Quantity " + quantity + ", Price Rs." + String.format("%.2f", price));
        }
    }
}