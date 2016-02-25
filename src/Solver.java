import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by cjamec1 on 2/24/2016.
 */
public class Solver {
    MinPQ<Node> queue = new MinPQ<>();
    Node solved;
    Board initial;
    ArrayList<Board> solution =  new ArrayList<>();
    int moves = 1;

    public Solver(Board initial){
        this.initial = initial;
        queue.insert(new Node(null, 0, initial));
        boolean isSolved = false;
        while(true){
            Node min = queue.delMin();
            if(min.board.isGoal()){
                solved = min;
                break;
            }

            for(Board board: min.board.neighbors()){
                queue.insert(new Node(min,moves,board));
            }
            moves++;

        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable()  {return false;}          // is the initial board solvable?
    public int moves(){return solution.size() - 1;}                     // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution()  {
        Node currentGoal = solved;
        while(currentGoal.previous != null){
            solution.add(currentGoal.board);
            currentGoal = currentGoal.previous;
        }

        solution.add(this.initial);
        Collections.reverse(solution);

        return solution;

    }    // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){}


    private class Node implements Comparable<Node>{
        private Node previous;
        private int moves;
        private Board board;

        public Node(Node previous, int moves, Board board){
            this.previous = previous;
            this.moves = moves;
            this.board = board;
        }

        @Override
        public int compareTo(Node o) {
            if(this.board.manhattan() + moves == o.board.manhattan() + o.moves ) return 0;
            if(this.board.manhattan() + moves < o.board.manhattan() + o.moves ) return -1;
            else  return 1;
        }
    }
}
