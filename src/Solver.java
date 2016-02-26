import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;

public class Solver {
    private MinPQ<Node> queue = new MinPQ<>();
    private MinPQ<Node> twinQueue = new MinPQ<>();
    private Node solved;
    private Node twinSolved;
    private Board initial;
    private ArrayList<Board> solution =  new ArrayList<>();

    public Solver(Board initial){
        this.initial = initial;
        queue.insert(new Node(null, 0, initial));
        twinQueue.insert(new Node(null, 0, initial.twin()));
        while(solved == null && twinSolved == null && !queue.isEmpty()){

            Node min = queue.delMin();
            if(min.board.isGoal()){
                solved = min;
            }

            for(Board board: min.board.neighbors()){
                if(min.previous == null || !board.equals(min.previous.board)){
                    queue.insert(new Node(min,min.moves + 1,board));
                }
            }


            Node twinMin = twinQueue.delMin();
            if(twinMin.board.isGoal()){
                twinSolved = min;
            }

            for(Board board: twinMin.board.neighbors()){
                if(twinMin.previous == null || !board.equals(twinMin.previous.board)) {
                    twinQueue.insert(new Node(twinMin, twinMin.moves + 1, board));
                }
            }


        }
    }
    public boolean isSolvable()  {

        return twinSolved == null;

    }
    public int moves(){
        return (solved != null) ? solved.moves : -1;
    }

    public Iterable<Board> solution()  {
        if(solved != null){
            Node currentGoal = solved;
            while(currentGoal.previous != null){
                solution.add(currentGoal.board);
                currentGoal = currentGoal.previous;
            }

            solution.add(this.initial);
            Collections.reverse(solution);
            return solution;

        }
        return null;
    }

    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }


    }


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
