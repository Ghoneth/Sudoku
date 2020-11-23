package testjava;

import java.util.stream.IntStream;

public class TestJava {
    
    int[][]board = {
      { 7, 0, 0, 6, 0, 4, 0, 0, 2 },
      { 0, 0, 2, 0, 0, 0, 0, 6, 0 },
      { 0, 0, 0, 0, 0, 0, 3, 0, 0 },
      { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
      { 0, 0, 0, 8, 0, 0, 0, 0, 6 },
      { 0, 0, 0, 0, 0, 6, 0, 0, 0 },
      { 0, 0, 7, 0, 0, 0, 0, 0, 0 },
      { 6, 0, 0, 0, 0, 8, 0, 0, 0 },
      { 0, 2, 0, 5, 0, 0, 0, 0, 9 }
    };
    
    public static int BOARD__START__INDEX = 0;
    public static int BOARD__SIZE = 9;
    public static int NO__VALUE = 0;
    public static int MIN__VALUE = 1;
    public static int MAX__VALUE = 9;
    public static int SUBSECTION__SIZE = 3;
    
    public TestJava () {
        printBoard();
        System.out.println();
        boolean test = solve(board);
        printBoard();
    }
            
    private boolean solve(int[][]board) {
        for (int row = BOARD__START__INDEX; row < BOARD__SIZE; row++) {
            for (int column = BOARD__START__INDEX; column < BOARD__SIZE; column++) {
                if (board[row][column]== NO__VALUE) {
                    for (int k = MIN__VALUE; k <= MAX__VALUE; k++) {
                        board[row][column]= k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column]= NO__VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][]board, int row, int column) {
        return (rowConstraint(board, row)
          && columnConstraint(board, column)
          && subsectionConstraint(board, row, column));
    }

    private boolean rowConstraint(int[][]board, int row) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        return IntStream.range(BOARD__START__INDEX, BOARD__SIZE).allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean columnConstraint(int[][]board, int column) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        return IntStream.range(BOARD__START__INDEX, BOARD__SIZE).allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean subsectionConstraint(int[][]board, int row, int column) {
        boolean[]constraint = new boolean[BOARD__SIZE];
        int subsectionRowStart = (SUBSECTION__SIZE *(row/SUBSECTION__SIZE));
        int subsectionRowEnd = subsectionRowStart + SUBSECTION__SIZE;

        int subsectionColumnStart = (SUBSECTION__SIZE * (column/SUBSECTION__SIZE));
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION__SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    boolean checkConstraint(int[][]board, int row, boolean[]constraint, int column) {
        
        if (board[row][column]!= NO__VALUE) {
            if (!constraint[board[row][column]- 1]) {
                constraint[board[row][column]- 1]= true;
            } else {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int row = BOARD__START__INDEX; row < BOARD__SIZE; row++) {
            for (int column = BOARD__START__INDEX; column < BOARD__SIZE; column++) {
                System.out.print(board[row][column]+ " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
         TestJava test = new TestJava();
    }
}