import java.util.Random;
import java.util.Scanner;

public class project_1 {

    static char[][] arr = new char[3][3];
    static int moveCount = 0;
    static int player1Wins = 0;
    static int player2Wins = 0;
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    static String player1Name;
    static String player2Name;

    
    static void welcomeScreen() {
        System.out.println("===================================");
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Game developed by: Mahmoud Moheb");
        System.out.println("-----------------------------------");
        System.out.println("How to Play:");
        System.out.println("- Choose game mode: Human vs Computer or Human vs Human");
        System.out.println("- Enter your name(s)");
        System.out.println("- Players take turns to mark X or O on the 3x3 board");
        System.out.println("- First to align 3 marks horizontally, vertically, or diagonally wins");
        System.out.println("- If the board fills up without a winner, it's a Draw");
        System.out.println("Enjoy the game! ");
        System.out.println("===================================");
    }

   
    static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = '.';
            }
        }
        moveCount = 0;
    }

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    static boolean win(char player) {
        for (int i = 0; i < 3; i++) {
            if (arr[i][0] == player && arr[i][1] == player && arr[i][2] == player) {
                return true;
            }
            if (arr[0][i] == player && arr[1][i] == player && arr[2][i] == player) {
                return true;
            }
        }
        if (arr[0][0] == player && arr[1][1] == player && arr[2][2] == player) {
            return true;
        }
        if (arr[0][2] == player && arr[1][1] == player && arr[2][0] == player) {
            return true;
        }
        return false;
    }

   
    static void playerTurn(String name, char playerChar) {
        System.out.println(name + "'s Turn (" + playerChar + "): ");
        int a = input.nextInt();
        int b = input.nextInt();
        input.nextLine(); 

        if (a < 0 || a > 2 || b < 0 || b > 2 || arr[a][b] != '.') {
            System.out.println("Invalid move! Try again.");
            playerTurn(name, playerChar);
        } else {
            arr[a][b] = playerChar;
            moveCount++;
        }
    }


    static void computerTurn() {
        System.out.println(player2Name + "'s Turn (O): ");
        int a, b;
        do {
            a = random.nextInt(3);
            b = random.nextInt(3);
        } while (arr[a][b] != '.');
        arr[a][b] = 'O';
        moveCount++;
    }

    static void playSingleGame(int mode) {
        initBoard();
        printBoard();

        while (true) {

            if (mode == 1) { 
                playerTurn(player1Name, 'X');
                printBoard();

                if (win('X')) {
                    System.out.println(player1Name + " Won!");
                    player1Wins++;
                    break;
                }

                if (moveCount == 9) {
                    System.out.println("It's a Draw!");
                    break;
                }

                computerTurn();
                printBoard();

                if (win('O')) {
                    System.out.println(player2Name + " Won!");
                    player2Wins++;
                    break;
                }

                if (moveCount == 9) {
                    System.out.println("It's a Draw!");
                    break;
                }

            } else if (mode == 2) { 
                playerTurn(player1Name, 'X');
                printBoard();

                if (win('X')) {
                    System.out.println(player1Name + " Won!");
                    player1Wins++;
                    break;
                }

                if (moveCount == 9) {
                    System.out.println("It's a Draw!");
                    break;
                }

                playerTurn(player2Name, 'O');
                printBoard();

                if (win('O')) {
                    System.out.println(player2Name + " Won!");
                    player2Wins++;
                    break;
                }

                if (moveCount == 9) {
                    System.out.println("It's a Draw!");
                    break;
                }
            }
        }
    }

  
    static void gameLoop() {

        System.out.println("Choose Game Mode:");
        System.out.println("1 - Human vs Computer");
        System.out.println("2 - Human vs Human");
        int mode = input.nextInt();
        input.nextLine(); 

        if (mode == 1) {
            System.out.println("Enter your name: ");
            player1Name = input.nextLine();
            player2Name = "Computer";
        } else {
            System.out.println("Enter Player 1 name: ");
            player1Name = input.nextLine();
            System.out.println("Enter Player 2 name: ");
            player2Name = input.nextLine();
        }

        boolean keepPlaying = true;

        while (keepPlaying) {

            playSingleGame(mode);

            System.out.println("Score: " + player1Name + " " + player1Wins + " - " + player2Wins + " " + player2Name);

            System.out.println("Do you want to play again? (yes/no)");
            String response = input.nextLine().toLowerCase();

            if (response.equals("no")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thanks for playing! Final Score: " + player1Name + " " + player1Wins + " - " + player2Wins + " " + player2Name);
    }

    public static void main(String[] args) {
        welcomeScreen();
        gameLoop();
        input.close();
    }
}