import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by chad on 1/25/2016.
 */
public class Percolation {
    private WeightedQuickUnionUF qu;
    private boolean[][] state;
    private int size;
    private int virtualTopIndex;
    private int virtualBottomIndex;
    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        size = N;
        state = new boolean [size][size];
        qu = new WeightedQuickUnionUF(size*size + 2);
        virtualTopIndex = size*size + 1;
        virtualBottomIndex = size*size ;
    }

    public boolean isOpen(int i, int j) {
        validateParams(i,j);
        return state[i == 0 ? 0 : i-1][j == 0 ? 0 : j-1] == true;
    }

    public void open(int r, int c) {
        validateParams(r,c);
        int row = r-1;
        int col = c-1;
        state[row][col] = true;

        if(row == 0){
            qu.union(convert2Dto1D(row, col), virtualTopIndex);
        }

        if(row == size-1){
            qu.union(convert2Dto1D(row, col), virtualBottomIndex);
        }

        if(row != 0 && isOpen(r-1 , c)){
            qu.union(convert2Dto1D(row, col), convert2Dto1D(row-1, col));
        }

        if(row != size-1 && isOpen(r+1, c)){
            qu.union(convert2Dto1D(row, col), convert2Dto1D(row+1, col));
        }

        if(col != 0 && isOpen(r, c - 1)){
            qu.union(convert2Dto1D(row, col), convert2Dto1D(row, col-1));
        }

        if(c != size && isOpen(r, c+1)){
            qu.union(convert2Dto1D(row, col), convert2Dto1D(row, col+1));
        }

    }

    private void validateParams(int r,int c){
        if(r < 1 || r > size || c < 1 || c > size){
            throw new IndexOutOfBoundsException ();
        }
    }

    private int convert2Dto1D(int row, int col){
        return (row*size + col);
    }

    public boolean isFull(int row, int col) {
        if(row < 1 || row > size || col < 1 || col > size){
            throw new IndexOutOfBoundsException ();
        }

        return qu.connected(convert2Dto1D(row-1, col-1), virtualTopIndex);
    }

    public boolean percolates() {
        return qu.connected(virtualBottomIndex,virtualTopIndex);
    }

    private WeightedQuickUnionUF getQU(){
        return qu;
    }

    
}
