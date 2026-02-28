import java.util.Scanner;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

// 4003600000000014 >>>>> VISA
// 5500000000000004 >>>>>> MasterCard
// 340000000000009 >>>>>>> AMEX

public class CreditApp {

    static Scanner input = new Scanner(System.in);
    static ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {

        showWelcome();

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    processCard();
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    testMode();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        input.close();
    }

    

    public static void showWelcome() {
        System.out.println("=====================================");
        System.out.println("      CREDIT CARD VALIDATOR PRO      ");
        System.out.println("=====================================");
    }

    public static void showMenu() {
        System.out.println("\n1. Validate New Card");
        System.out.println("2. View Validation History");
        System.out.println("3. Test Mode (Sample Cards)");
        System.out.println("4. Exit");
        System.out.print("Choose option: ");
    }

    public static int getMenuChoice() {
        while (!input.hasNextInt()) {
            System.out.println("Enter a valid number.");
            input.next();
        }
        return input.nextInt();
    }



    public static void processCard() {
        input.nextLine(); 

        String cardNumber = getCardNumber();

        if (!isNumeric(cardNumber)) {
            System.out.println("Card must contain digits only.");
            return;
        }

        int sum = checksum(cardNumber);

        System.out.println("Checksum sum: " + sum);

        if (sum % 10 == 0)   {

            String type = detectType(cardNumber);

            System.out.println("VALID CARD");
            System.out.println("Type: " + type);
            System.out.println("Formatted: " + formatCard(cardNumber));
            System.out.println("Masked: " + maskCard(cardNumber));

            validateExpiration();
            validateCVV(type);

            history.add(maskCard(cardNumber) + " - " + type);

        } else {
            System.out.println("INVALID CARD");
        }
    }

   

    public static int checksum(String cardNo) {
        int sum = 0;
        int length = cardNo.length();

        for (int i = length - 1; i >= 0; i--) {
            int digit = cardNo.charAt(i) - '0';

            if ((length - i) % 2 == 0) {
                digit *= 2;

                if (digit > 9) {
                    digit = digit / 10 + digit % 10;
                }
            }

            sum += digit;
        }

        return sum;
    }

   

    public static String detectType(String cd) {

        int length = cd.length();

        if (length == 15 && (cd.startsWith("34") || cd.startsWith("37"))) {
            return "AMEX";
        }
        else if (length == 16 &&
                (cd.startsWith("51") || cd.startsWith("52") ||
                 cd.startsWith("53") || cd.startsWith("54") ||
                 cd.startsWith("55"))) {
            return "MASTERCARD";
        }
        else if ((length == 13 || length == 16) && cd.startsWith("4")) {
            return "VISA";
        }
        else {
            return "UNKNOWN";
        }
    }

    

    public static String getCardNumber() {
        System.out.print("Enter Card Number: ");
        return input.nextLine().trim();
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static void validateExpiration() {

        input.nextLine(); // clear

        System.out.print("Enter Expiration Date (MM/YY): ");
        String exp = input.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(exp, formatter);
            YearMonth now = YearMonth.now();

            if (expiry.isBefore(now)) {
                System.out.println("Card is expired.");
            } else {
                System.out.println("Expiration valid.");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    public static void validateCVV(String type) {

        System.out.print("Enter CVV: ");
        String cvv = input.next();

        if (!isNumeric(cvv)) {
            System.out.println("Invalid CVV format.");
            return;
        }

        if (type.equals("AMEX") && cvv.length() == 4) {
            System.out.println("Valid AMEX CVV.");
        }
        else if (!type.equals("AMEX") && cvv.length() == 3) {
            System.out.println("Valid CVV.");
        }
        else {
            System.out.println("Invalid CVV length.");
        }
    }

    

    public static String formatCard(String number) {

        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < number.length(); i++) {
            formatted.append(number.charAt(i));

            if ((i + 1) % 4 == 0 && i != number.length() - 1) {
                formatted.append(" ");
            }
        }

        return formatted.toString();
    }

    public static String maskCard(String number) {

        if (number.length() <= 4) return number;

        String last4 = number.substring(number.length() - 4);
        return "**** **** **** " + last4;
    }

    

    public static void showHistory() {

        if (history.isEmpty()) {
            System.out.println("No validation history.");
            return;
        }

        System.out.println("Validation History:");
        for (String record : history) {
            System.out.println(record);
        }
    }


    public static void testMode() {

        System.out.println("\nRunning sample tests...");

        String[] testCards = {
            "4111111111111111",
            "5500000000000004",
            "340000000000009",
            "1234567890123456"
        };

        for (String card : testCards) {
            int sum = checksum(card);
            String type = detectType(card);

            System.out.println("-------------------------");
            System.out.println("Card: " + maskCard(card));
            System.out.println("Checksum: " + sum);
            System.out.println("Valid: " + (sum % 10 == 0));
            System.out.println("Type: " + type);
        }
    }
}