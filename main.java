import java.util.Scanner;
public class main {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Sodoku Solver");
        System.out.println("How would you like to input the board (1- by indexes, 2- all one by one [empty = 0], 3- an example)");
        int n = in.nextInt();
        switch (n)
        {
            case (1):
                int[][] board = helpSoduku.createEmptyBoard();
                System.out.println("To stop inputting values, enter -1 in cell's value");
                System.out.println();
                int row = 1, col = 1 , value = 0;
                while(value != -1) {
                    board[row-1][col-1] = value;
                    helpSoduku.printBoard(board);
                    row = 0; col = 0; value = 0;
                    while (row < 1 || row > 9) {
                        System.out.println("Enter row index (1-9 only): ");
                        row = in.nextInt();
                    }
                    while (col < 1 || col > 9) {
                        System.out.println("Enter col index (1-9 only): ");
                        col = in.nextInt();
                    }
                    while (value != -1 && (value < 1) || value > 9) {
                        System.out.println("Enter cell's value index (1-9 only): ");
                        value = in.nextInt();
                    }
                }
                System.out.println();
                helpSoduku.printBoard(board);
                System.out.println();
                System.out.println("Answer:");
                System.out.println();
                if (helpSoduku.solveSoduku(board))
                    helpSoduku.printBoard(board);
                else
                    System.out.println("No Answer");
                break;

            case(2):
                int value2;
                int[][] board2 = helpSoduku.createEmptyBoard();
                System.out.println("You are going to type 81 digits, sorry");
                System.out.println();
                for (int k = 0; k < 81; k++)
                {
                    System.out.println("Enter " + (k/9+1) + "," + ((k)%9+1) + " cell's value:");
                    value2 = in.nextInt();
                    while (value2 < 0 || value2 > 9)
                    {
                        System.out.println("Input must be between 0-9 only");
                        value2 = in.nextInt();
                    }
                    board2[k/9][k%9] = value2;
                }
                System.out.println();
                helpSoduku.printBoard(board2);
                System.out.println();
                System.out.println("Answer:");
                System.out.println();
                if (helpSoduku.solveSoduku(board2))
                    helpSoduku.printBoard(board2);
                else
                    System.out.println("No Answer");
                break;
            case (3):
            {
                int[][] example = {{0,6,5,0,0,0,3,0,0},
                        {2,0,0,0,6,7,9,0,0},
                        {0,4,0,3,0,0,0,0,1},
                        {0,0,6,0,5,0,0,0,4},
                        {0,0,0,4,0,2,0,0,0},
                        {7,0,0,0,8,0,1,0,0},
                        {6,0,0,0,0,4,0,1,0},
                        {0,0,8,5,7,0,0,0,6},
                        {0,0,1,0,0,0,8,3,0}};

                helpSoduku.printBoard(example);
                System.out.println();
                System.out.println("Answer:");
                System.out.println();
                helpSoduku.solveSoduku(example);
                helpSoduku.printBoard(example);
            }
        }
        System.out.println();
        System.out.println("Thank you for using my sudoku solver even though" +
                " there are millions of other and probably better solvers");

    }
}

