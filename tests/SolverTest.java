import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cjamec1 on 2/24/2016.
 */
public class SolverTest {
    @Test
    public void testStuff(){
        int[][] board = {
                {0,1,3},
                {4,2,5},
                {7,8,6}
        };
        Board b = new Board(board);

        Solver solver = new Solver(b);
        for(Board result: solver.solution()){
            System.out.println(result.toString());
        }
    }

}