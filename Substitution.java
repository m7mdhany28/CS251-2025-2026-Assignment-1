import java.util.Scanner;

// ABCDEFGHIJKLMNOPQRSTUVWXYZ
// NQXPOMAFTRHLZGECYJIUWSKDVB
// Before: Hello
// After:  Folle
//---------------------------------2
// 0123456789
// 6283940571
// Before: 2007
// After:  8665
public class Substitution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======| Encryption Program |======");
            System.out.println("1- Encrypt Text");
            System.out.println("2- Encrypt Numbers");
            System.out.println("3- Decrypt Text");
            System.out.println("4- Decrypt Numbers");
            System.out.println("5- Exit");
            System.out.println("---> Choose <---");
            String choice = sc.nextLine();
            System.out.print("==================================\n");

            if (choice.equals("1")) {
                encryptText(sc);
            } else if (choice.equals("2")) {
                encryptNumbers(sc);
            } else if (choice.equals("3")) {
                decryptText(sc);
            } else if (choice.equals("4")) {
                decryptNumbers(sc);
            } else if (choice.equals("5")) {
                System.out.println("Salam!");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static int CheckNum(String Key) {
        char[] KeyArr = Key.toCharArray();
        if (Key.length() != 10) {
            System.out.println("Key must contain 10 Numbers.");
            return 1;
        }
        for (int i = 0; i < Key.length(); i++) {
            if (!Character.isDigit(KeyArr[i])) {
                System.out.println("Key must only contain Numbers.");
                return 1;
            }
        }
        int count = 0;
        for (int i = 0; i < Key.length(); i++) {
            for (int j = 0; j < Key.length(); j++) {
                if ((KeyArr[i]) == (KeyArr[j])) {
                    count++;
                }
            }
        }
        if (count > 10) {
            System.out.println("Key must not contain repeated Numbers.");
            return 1;
        }
        return 0;
    }

    public static int CheckAlpha(String Key) {
        char[] KeyArr = Key.toCharArray();
        if (Key.length() != 26) {
            System.out.println("Key must contain 26 characters.");
            return 1;
        }
        for (int i = 0; i < Key.length(); i++) {
            if (!Character.isAlphabetic(KeyArr[i])) {
                System.out.println("Key must only contain alphabetic characters.");
                return 1;
            }
        }
        int count = 0;
        for (int i = 0; i < Key.length(); i++) {
            for (int j = 0; j < Key.length(); j++) {
                if (Character.toUpperCase(KeyArr[i]) == Character.toUpperCase(KeyArr[j])) {
                    count++;
                }
            }
        }
        if (count > 26) {
            System.out.println("Key must not contain repeated characters.");
            return 1;
        }
        return 0;
    }

    public static void encryptText(Scanner sc) {
        System.out.println("Enter your KEY.");
        String Key = sc.nextLine();

        if (CheckAlpha(Key) != 0) {
            return;
        }

        Key = Key.toUpperCase();

        String ABCs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println("plaintext:");
        String plaintext = sc.nextLine();

        char[] text = plaintext.toCharArray();

        for (int i = 0, n = text.length; i < n; i++) {
            if (Character.isLowerCase(text[i])) {
                for (int j = 0, m = ABCs.length(); j < m; j++) {
                    if (Character.toUpperCase(text[i]) == ABCs.charAt(j)) {
                        text[i] = Character.toLowerCase(Key.charAt(j));
                        break;
                    }
                }
            } else if (Character.isUpperCase(text[i])) {
                for (int j = 0, m = ABCs.length(); j < m; j++) {
                    if (text[i] == ABCs.charAt(j)) {
                        text[i] = Key.charAt(j);
                        break;
                    }
                }
            }
        }

        plaintext = new String(text);

        System.out.println("ciphertext:\n" + plaintext);
    }

    public static void encryptNumbers(Scanner sc) {
        System.out.println("Enter your KEY.");
        String Key = sc.nextLine();

        if (CheckNum(Key) != 0) {
            return;
        }

        String N123s = "0123456789";

        System.out.println("plainNum:");
        String plainNum = sc.nextLine();

        char[] num = plainNum.toCharArray();

        for (int i = 0, n = num.length; i < n; i++) {
            for (int j = 0, m = N123s.length(); j < m; j++) {
                if (num[i] == N123s.charAt(j)) {
                    num[i] = Key.charAt(j);
                    break;
                }
            }
        }
        plainNum = new String(num);

        System.out.println("cipherNum:\n" + plainNum);
    }

    public static void decryptText(Scanner sc) {
        System.out.println("Enter your KEY.");
        String Key = sc.nextLine();

        if (CheckAlpha(Key) != 0) {
            return;
        }

        Key = Key.toUpperCase();

        String ABCs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.println("ciphertext:");
        String ciphertext = sc.nextLine();

        char[] text = ciphertext.toCharArray();

        for (int i = 0, n = text.length; i < n; i++) {
            if (Character.isLowerCase(text[i])) {
                for (int j = 0, m = Key.length(); j < m; j++) {
                    if (Character.toUpperCase(text[i]) == Key.charAt(j)) {
                        text[i] = Character.toLowerCase(ABCs.charAt(j));
                        break;
                    }
                }
            } else if (Character.isUpperCase(text[i])) {
                for (int j = 0, m = Key.length(); j < m; j++) {
                    if (text[i] == Key.charAt(j)) {
                        text[i] = ABCs.charAt(j);
                        break;
                    }
                }
            }
        }

        ciphertext = new String(text);

        System.out.println("plaintext:\n" + ciphertext);

    }

    public static void decryptNumbers(Scanner sc) {
        System.out.println("Enter your KEY.");
        String Key = sc.nextLine();

        if (CheckNum(Key) != 0) {
            return;
        }

        String N123s = "0123456789";

        System.out.println("cipherNum:");
        String cipherNum = sc.nextLine();

        char[] num = cipherNum.toCharArray();

        for (int i = 0, n = num.length; i < n; i++) {
            for (int j = 0, m = Key.length(); j < m; j++) {
                if (num[i] == Key.charAt(j)) {
                    num[i] = N123s.charAt(j);
                    break;
                }
            }
        }

        cipherNum = new String(num);

        System.out.println("plainNum:\n" + cipherNum);
    }
}