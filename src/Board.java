import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int dimension;
    private int[][] board;
    public Board(int[][] blocks)  {
        dimension = blocks.length;
        board = blocks;
    }

    public int dimension()  {return dimension;}

    public int hamming() {
        int incorrect = 0;
        int expected = 1;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){

                if(board[i][j] != 0 && board[i][j] != expected){
                    incorrect++;
                }
                expected++;
            }
        }

        return incorrect;
    }

    public int manhattan() {
        int manhattan = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                int value = board[i][j];
                if(value != 0){
                    int mod = value % dimension;
                    int correctColumn = (mod == 0) ? dimension - 1 : mod - 1 ;
                    int correctRow = mod == 0 ? (value / dimension) - 1 : value/dimension;
                    manhattan += Math.abs(correctRow - i) + Math.abs(correctColumn - j);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public Board twin() {
        int[] zeroCoordinates = getZeroCoordinates();
        int row = zeroCoordinates[0];
        int[][] copy = copyBoard();
        int[][] swap =  (row == 0) ? swap(dimension -1, 0, dimension -1, 1, copy) : swap(0, 0, 0, 1, copy);

        return new Board(swap);
    }

    private int[][] swap(int x1, int y1, int x2, int y2, int[][] original){
        int temp = original[x1][y1];
        original[x1][y1] = original[x2][y2];
        original[x2][y2] = temp;
        return original;
    }

    public boolean equals(Object y) {
        Board that = (Board)y;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] != that.board[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors(){
        ArrayList<Board> neighbors = new ArrayList<>();

        int[] zeroCoordinates = getZeroCoordinates();
        int row = zeroCoordinates[0];
        int col = zeroCoordinates[1];

        if (row > 0) {
            neighbors.add(getNorthernNeighbor(row, col));
        }

        if (row < dimension - 1) {
            neighbors.add(getSouthernNeighbor(row, col));
        }

        if (col > 0) {
            neighbors.add(getWesternNeighbor(row, col));
        }

        if (col < dimension - 1) {
            neighbors.add(getEasternNeighbor(row, col));
        }


        return neighbors;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(dimension).append("\n");
        for(int[] list : board){
            for(int element: list){
                result.append(" ");
                result.append(element);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
        }

    public static void main(String[] args){} // unit tests (not graded)

    private Board getNorthernNeighbor(int row, int col){
        int[][] neighborCopy = copyBoard();
        int north = neighborCopy[row - 1][col];
        neighborCopy[row][col] = north;
        neighborCopy[row - 1][col] = 0;
        return new Board(neighborCopy);
    }

    private Board getSouthernNeighbor(int row, int col){
        int[][] neighborCopy = copyBoard();
        int south = neighborCopy[row + 1][col];
        neighborCopy[row][col] = south;
        neighborCopy[row + 1][col] = 0;
        return new Board(neighborCopy);
    }

    private Board getWesternNeighbor(int row, int col){
        int[][] neighborCopy = copyBoard();
        int west = neighborCopy[row][col - 1];
        neighborCopy[row][col] = west;
        neighborCopy[row][col - 1] = 0;
        return new Board(neighborCopy);
    }

    private Board getEasternNeighbor(int row, int col){
        int[][] neighborCopy = copyBoard();
        int east = neighborCopy[row][col + 1];
        neighborCopy[row][col] = east;
        neighborCopy[row][col + 1] = 0;
        return new Board(neighborCopy);
    }

    private int[][] copyBoard(){
        return Arrays.stream(board)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);
    }

    private int[] getZeroCoordinates(){
        int[] coordinates = new int[2];
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == 0){
                    coordinates[0] = i;
                    coordinates[1] = j;
                    break;
                }
            }
        }
        return coordinates;
    }
}
