import java.util.ArrayList;

/**
 * Created by cjamec1 on 2/24/2016.
 */
public class Solver {
    public Solver(Board initial){




    }           // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable()  {return false;}          // is the initial board solvable?
    public int moves(){return 1;}                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution()  {return new ArrayList<Board>();}    // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){}
}
