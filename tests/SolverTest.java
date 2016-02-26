import org.junit.Test;

public class SolverTest {
    @Test
    public void testStuff(){
        int[][] board = {

                {1,0},
                {2,3}

        };
        Board b = new Board(board);

        Solver solver = new Solver(b);
        for(Board result: solver.solution()){
            System.out.println(result.toString());
        }
    }



}