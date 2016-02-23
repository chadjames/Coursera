import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by cjamec1 on 2/23/2016.
 */
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
    }                 // sum of Manhattan distances between blocks and goal

    public boolean isGoal() {
        return manhattan() == 0;
    }               // is this board the goal board?

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

    }       // does this board equal y?

    public Iterable<Board> neighbors(){
        ArrayList<Board> neighbors = new ArrayList<>();
        int zeroCoordinates[] = {-1,-1};
        int[][] neighborCopy = Arrays.stream(board)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);


        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if(board[i][j] == 0){
                    zeroCoordinates[0] = i;
                    zeroCoordinates[1] = j;
                    break;
                }
            }
        }



        if(zeroCoordinates[1] == 0){
            neighbors.add(getNorthernNeighbor(zeroCoordinates));
            neighbors.add(getSouthernNeighbor(zeroCoordinates));
            neighbors.add(getEasternNeighbor(zeroCoordinates));



        }
        if(zeroCoordinates[1] == dimension - 1){

        }
        if(zeroCoordinates[0] == 0){

        }
        if(zeroCoordinates[0] == dimension - 1){

        }


        neighbors.add(getNorthernNeighbor(zeroCoordinates));
        neighbors.add(getSouthernNeighbor(zeroCoordinates));
        neighbors.add(getEasternNeighbor(zeroCoordinates));
        neighbors.add(getWesternNeighbor(zeroCoordinates));


        return neighbors;
    }     // all neighboring boards

    public String toString() {return null;}              // string representation of this board (in the output format specified below)

    public static void main(String[] args){} // unit tests (not graded)

    private Board getNorthernNeighbor(int[] zeroCoordinates){
        int[][] neighborCopy = copyBoard();
        int north = neighborCopy[zeroCoordinates[0]][zeroCoordinates[1] + 1];
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1]] = north;
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1] + 1] = 0;
        return new Board(neighborCopy);
    }
    private Board getSouthernNeighbor(int[] zeroCoordinates){
        int[][] neighborCopy = copyBoard();
        int south = neighborCopy[zeroCoordinates[0]][zeroCoordinates[1] - 1];
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1]] = south;
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1] - 1] = 0;
        return new Board(neighborCopy);
    }
    private Board getWesternNeighbor(int[] zeroCoordinates){
        int[][] neighborCopy = copyBoard();
        int west = neighborCopy[zeroCoordinates[0] + 1][zeroCoordinates[1]];
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1]] = west;
        neighborCopy[zeroCoordinates[0] + 1][zeroCoordinates[1]] = 0;
        return new Board(neighborCopy);
    }
    private Board getEasternNeighbor(int[] zeroCoordinates){
        int[][] neighborCopy = copyBoard();
        int east = neighborCopy[zeroCoordinates[0] - 1][zeroCoordinates[1]];
        neighborCopy[zeroCoordinates[0]][zeroCoordinates[1]] = east;
        neighborCopy[zeroCoordinates[0] - 1][zeroCoordinates[1]] = 0;
        return new Board(neighborCopy);
    }
    private int[][] copyBoard(){
        return Arrays.stream(board)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);
    }
}
