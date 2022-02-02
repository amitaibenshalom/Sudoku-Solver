public class helpSoduku {

    public static int[][] createEmptyBoard()
    {
        int[][] B = new int[9][9];
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++)
                B[i][j] = 0;
        }
        return B;
    }

    public static void printBoard(int[][] B)
    {
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[0].length; j++)
                System.out.print(B[i][j] + " ");
            System.out.println();
        }
    }

    public static boolean isFull(int[][] board)
    {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == 0)
                    return false;
        return true;
    }

    public static boolean isAvailable(int[][] board, int i, int j, int d)
    {
        for (int k = 0; k < 9; k++)
            if (board[i][k] == d || board[k][j] == d || board[(i/3)*3+k/3][(j/3)*3+k%3] == d)
                return false;
        return true;

    }

    public static boolean[] getAvailableDigits(int[][] board, int i, int j)
    {
        boolean[] availables = new boolean[9];
        for (int k = 0; k < 9; k++)
            availables[k] = isAvailable(board, i, j, k+1);
        return availables;
    }

    public static int countAvailable(boolean[] arr)
    {
        int count = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == true)
                count++;
        return count;
    }

    public static int getFinalDigit(boolean[] arr)
    {
        int i = 0;
        while (arr[i] != true && i < arr.length)
            i++;
        return (i+1);
    }

    public static boolean solveSoduku(int[][] board)
    {
        //a more efficient function to solve any easy sudokus or at least to get some digits right
        //for the big and not very efficient function later.

        boolean[] availableDigits;
        boolean isChanged = true;
        while(!isFull(board) && isChanged)
        {
            isChanged = false;
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    if (board[i][j] == 0) {
                        availableDigits = getAvailableDigits(board, i, j);
                        if (countAvailable(availableDigits) == 1) {
                            board[i][j] = getFinalDigit(availableDigits);
                            isChanged = true;
                        }
                    }
                }
            }
        }
        if (!isFull(board))
            return (solveSoduku_rec(board,0,0));
        return true;
    }

    public static boolean solveSoduku_rec(int[][] board, int i, int j)
    {
        //if the normal solve function didn't work, solve it with backtracking

        if (i == 8 && j == 9)
            return true;
        if (j == 9)
        {
            i++;
            j = 0;
        }
        if (board[i][j] == 0)
        {
            for (int k = 1; k < 10; k++)
            {
                if (isAvailable(board, i, j, k)) {
                    board[i][j] = k;
                    if (solveSoduku_rec(board, i, j+1))
                        return true;
                }
                board[i][j] = 0;
            }
            return false;
        }
        return (solveSoduku_rec(board, i, j+1));
    }


//    public static int[][] transposeBoard(int[][] board)
//    {
//        int[][] TBoard = new int[board[0].length][board.length];
//        for (int i = 0; i < TBoard.length; i++)
//            for (int j = 0; j < TBoard[0].length; j++)
//                TBoard[i][j] = board[j][i];
//        return TBoard;
//    }

//    public static boolean isValidRow(int[] row)
//    {
//        int[] temp = {0,0,0,0,0,0,0,0,0};
//        for (int i = 0; i < row.length; i++) {
//            temp[row[i] - 1]++;
//            if (temp[row[i] - 1] > 1)
//                return false;
//        }
//        return true;
//    }

//    public static boolean isSolved(int[][] board)
//    {
//        for (int i = 0; i < board.length; i++)
//        {
//            if (!isValidRow(board[i]) || !isValidRow(transposeBoard(board)[i]))
//                return false;
//        }
//        return true;
//    }
}
