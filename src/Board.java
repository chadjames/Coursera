import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int dimension;
    private int[][] board;
    public Board(int[][] blocks)  {
        dimension = blocks.length;
        board = blocks;

    }

    public int dimension()  {return dimension;}               // board dimension N

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
    }                  // number of blocks out of place

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

    public Board twin() {return null;}                   // a board that is obtained by exchanging any pair of blocks

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
        int zeroCoordinates[] = {-1,-1};
        int[][] neighborCopy = Arrays.stream(board)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);

        int row = 0, col = 0;
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if(col == 0){
            neighbors.add(getEasternNeighbor(row, col));
            if(row == 0){
                neighbors.add(getSouthernNeighbor(row, col));
            }else if(row == dimension - 1){
                neighbors.add(getNorthernNeighbor(row, col));
            }else {
                neighbors.add(getNorthernNeighbor(row, col));
                neighbors.add(getSouthernNeighbor(row, col));
            }
            return neighbors;
        }

        if(col == dimension - 1){
            neighbors.add(getWesternNeighbor(row, col));
            if(row == 0){
                neighbors.add(getSouthernNeighbor(row, col));
            }else if(row == dimension - 1){
                neighbors.add(getNorthernNeighbor(row, col));
            }else {
                neighbors.add(getNorthernNeighbor(row, col));
                neighbors.add(getSouthernNeighbor(row, col));
            }
            return neighbors;
        }

        if(row == 0){
            neighbors.add(getSouthernNeighbor(row, col));
            if(col == 0){
                neighbors.add(getEasternNeighbor(row, col));
            }else if(col == dimension - 1){
                neighbors.add(getWesternNeighbor(row, col));
            }else {
                neighbors.add(getEasternNeighbor(row, col));
                neighbors.add(getWesternNeighbor(row, col));
            }
            return neighbors;
        }
        
        if(row == dimension - 1){
            neighbors.add(getNorthernNeighbor(row, col));
            if(col == 0){
                neighbors.add(getEasternNeighbor(row, col));
            }else if(col == dimension - 1){
                neighbors.add(getWesternNeighbor(row, col));
            }else {
                neighbors.add(getEasternNeighbor(row, col));
                neighbors.add(getWesternNeighbor(row, col));
            }
            return neighbors;
        }

        neighbors.add(getNorthernNeighbor(row, col));
        neighbors.add(getSouthernNeighbor(row, col));
        neighbors.add(getEasternNeighbor(row, col));
        neighbors.add(getWesternNeighbor(row, col));

        return neighbors;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        for(int[] list : board){
            for(int element: list){
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
}
