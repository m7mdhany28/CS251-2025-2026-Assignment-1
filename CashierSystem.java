import java.util.ArrayList;
import java.util.Scanner;

class Subject {
    String name;
    double price;

    Subject(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class CashierSystem {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Subject> subjects = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            printMenu();
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addSubject();
                    break;
                case 2:
                    removeSubject();
                    break;
                case 3:
                    printInvoice();
                    break;
                case 4:
                    System.out.println("Thanks");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);
    }

    public static void printMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1- Add Subject");
        System.out.println("2- Remove Subject");
        System.out.println("3- Print Invoice");
        System.out.println("4- Exit");
        System.out.print("Choose: ");
    }

    public static void addSubject() {
        System.out.print("Enter Subject Name: ");
        String name = input.nextLine();

        System.out.print("Enter Price: ");
        double price = input.nextDouble();
        input.nextLine();

        if (price <= 0) {
            System.out.println("Price must be positive");
            return;
        }

        subjects.add(new Subject(name, price));
        System.out.println("Added Successfully");
    }

    public static void removeSubject() {
        System.out.print("Enter Subject Name To Remove: ");
        String name = input.nextLine();

        boolean found = false;

        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).name.equalsIgnoreCase(name)) {
                subjects.remove(i);
                found = true;
                System.out.println("Removed Successfully");
                break;
            }
        }

        if (!found) {
            System.out.println("Not Found");
        }
    }

    public static void printInvoice() {
        if (subjects.isEmpty()) {
            System.out.println("No Subjects Added Yet.");
            return;
        }

        double total = 0;

        System.out.println("\n===== INVOICE =====");

        for (Subject s : subjects) {
            System.out.println(s.name + " == " + s.price);
            total += s.price;
        }

        System.out.println("-------------------");
        System.out.println("Subtotal = " + total);

       
        System.out.print("Do you want to apply a discount? ");
        String applyDiscount = input.nextLine();

        if (applyDiscount.equalsIgnoreCase("yes")) {
            total = discount(total); 
        }

        System.out.println("Total After Discount = " + total);
    }

    public static double discount(double total) {
        System.out.print("Enter discount percentage: ");
        double percent = input.nextDouble();
        input.nextLine(); 

        if (percent < 0) {
            System.out.println("Invalid discount");
            return total;
        }

        double discountAmount = total * (percent / 100);
        return total - discountAmount;
    }
}
