package hotelproject;

import java.util.*;

class Customer {
    String name;
    int roomNumber;
    String phone;

    public Customer(String name, int roomNumber, String phone) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.phone = phone;
    }

    public void display() {
        System.out.println("Name: " + name + ", Room: " + roomNumber + ", Phone: " + phone);
    }
}

class MenuItem {
    int id;
    String name;
    double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void display() {
        System.out.println(id + ". " + name + " - ₹" + price);
    }
}

public class Management {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<MenuItem> menu = new ArrayList<>();
    static HashMap<Integer, ArrayList<MenuItem>> orders = new HashMap<>();

    public static void main(String[] args) {
        initializeMenu();

        while (true) {
            System.out.println("\n==== HOTEL MANAGEMENT ====");
            System.out.println("1. Add New Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Show Menu");
            System.out.println("4. Place Order");
            System.out.println("5. View Bill");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> viewCustomers();
                case 3 -> showMenu();
                case 4 -> placeOrder();
                case 5 -> viewBill();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void initializeMenu() {
        menu.add(new MenuItem(1, "Idli", 30));
        menu.add(new MenuItem(2, "Dosa", 40));
        menu.add(new MenuItem(3, "Pongal", 50));
        menu.add(new MenuItem(4, "Coffee", 20));
    }

    static void addCustomer() {
        sc.nextLine();  // consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter room number: ");
        int room = sc.nextInt();
        sc.nextLine();  // consume newline
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();
        customers.add(new Customer(name, room, phone));
        System.out.println("Customer added!");
    }

    static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customers) {
            c.display();
        }
    }

    static void showMenu() {
        System.out.println("---- MENU ----");
        for (MenuItem item : menu) {
            item.display();
        }
    }

    static void placeOrder() {
        System.out.print("Enter room number: ");
        int room = sc.nextInt();
        showMenu();
        System.out.print("Enter item ID to order (0 to finish): ");
        int id;
        ArrayList<MenuItem> orderList = new ArrayList<>();

        while ((id = sc.nextInt()) != 0) {
            boolean found = false;
            for (MenuItem item : menu) {
                if (item.id == id) {
                    orderList.add(item);
                    found = true;
                    System.out.println(item.name + " added to order.");
                }
            }
            if (!found) {
                System.out.println("Invalid item ID!");
            }
        }
        if (!orderList.isEmpty()) {
            orders.put(room, orderList);
            System.out.println("Order placed!");
        }
    }

    static void viewBill() {
        System.out.print("Enter room number to view bill: ");
        int room = sc.nextInt();
        if (orders.containsKey(room)) {
            ArrayList<MenuItem> orderList = orders.get(room);
            double total = 0;
            System.out.println("---- BILL ----");
            for (MenuItem item : orderList) {
                System.out.println(item.name + " - ₹" + item.price);
                total += item.price;
            }
            System.out.println("Total: ₹" + total);
        } else {
            System.out.println("No order found for this room.");
        }
    }
}
