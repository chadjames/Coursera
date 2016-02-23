import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by cjamec1 on 2/23/2016.
 */
public class BoardTest {

    @Test
    public void testDimension() throws Exception {
        Board b = new Board(new int[5][5]);
        assert 5 == b.dimension();
    }

    @Test
    public void testHammingReturns0ForSolvedBoard() throws Exception {
        int[][] board = new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,0}
        };

        Board b = new Board(board);
        assert 0 == b.hamming();
    }

    @Test
    public void testHammingReturns1WhenCorrectRow() throws Exception {
        int[][] board = {
            {1,2,3},
            {4,5,6},
            {7,0,8}
        };

        Board b = new Board(board);
        assert 1 == b.hamming();
    }

    @Test
    public void testHammingReturns1WhenInCorrectRow() throws Exception {
        int[][] board = {
            {1,2,3},
            {4,5,8},
            {7,0,6}
        };

        Board b = new Board(board);
        assert 2 == b.hamming();
    }

    @Test
    public void testHammingReturns1WhenInCorrectColumn() throws Exception {
        int[][] board = {
            {1,2,3},
            {4,8,5},
            {7,6,0}
        };

        Board b = new Board(board);
        assert 3 == b.hamming();
    }

    @Test
    public void testHammingReturns1WhenAllIncorrect() throws Exception {
        int[][] board = {
            {3,1,2},
            {5,6,4},
            {8,7,0}
        };

        Board b = new Board(board);
        assert 8 == b.hamming();
    }

    @Test
    public void testManhattanForSolvedBoard() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };

        Board b = new Board(board);
        assert 0 == b.manhattan();

    }
    @Test
    public void testManhattanFor1MoveSameRow() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,5,6},
                {7,0,8}
        };

        Board b = new Board(board);
        assert 1 == b.manhattan();

    }
    @Test
    public void testManhattanFor1MoveDifferentRow() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,8,6},
                {7,5,0}
        };

        Board b = new Board(board);
        assert 2 == b.manhattan();

    }
    @Test
    public void testManhattanFor1MoveMultipleRow() throws Exception {
        int[][] board = {
                {1,8,3},
                {4,0,6},
                {7,5,2}
        };

        Board b = new Board(board);
        assert 6 == b.manhattan();

    }
    @Test
    public void testManhattanForMultipleRowsAndColumns() throws Exception {
        int[][] board = {
                {4,8,7},
                {1,0,6},
                {3,5,2}
        };

        Board b = new Board(board);
        assert 16 == b.manhattan();

    }

    @Test
    public void testIsGoalTrue() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };

        Board b = new Board(board);
        assertTrue(b.isGoal());
    }
    @Test
    public void testIsGoalFalse() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,5,6},
                {7,0,8}
        };

        Board b = new Board(board);
        assertFalse(b.isGoal());
    }

    @Test
    public void testTwin() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        int[][] board1= {
                {1,2,3},
                {4,0,6},
                {5,7,8}
        };

        int[][] board2 = {
                {1,2,3},
                {4,0,6},
                {5,7,8}
        };

        Board b1 = new Board(board1);
        Board b2 = new Board(board2);

        assertTrue(b1.equals(b2));

    }

    @Test
    public void testNeighborsAll() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,0,6},
                {5,7,8}
        };

        int[][] n1 = {
                {1,0,3},
                {4,2,6},
                {5,7,8}
        };
        int[][] n2 = {
                {1,2,3},
                {4,7,6},
                {5,0,8}
        };
        int[][] n3 = {
                {1,2,3},
                {0,4,6},
                {5,7,8}
        };
        int[][] n4 = {
                {1,2,3},
                {4,6,0},
                {5,7,8}
        };

        Board b = new Board(board);
        Board b1 = new Board(n1);
        Board b2 = new Board(n2);
        Board b3 = new Board(n3);
        Board b4 = new Board(n4);
        ArrayList<Board> result = (ArrayList<Board>)b.neighbors();

        assertEquals(4, result.size());
        assertTrue(result.contains(b1));
        assertTrue(result.contains(b2));
        assertTrue(result.contains(b3));
        assertTrue(result.contains(b4));

    }
    @Test
    public void testNeighbors0ColMiddleRow() throws Exception {
        int[][] board = {
                {1,2,3},
                {0,4,6},
                {5,7,8}
        };

        int[][] n1 = {
                {1,2,3},
                {4,0,6},
                {5,7,8}
        };
        int[][] n2 = {
                {1,2,3},
                {5,4,6},
                {0,7,8}
        };
        int[][] n3 = {
                {0,2,3},
                {1,4,6},
                {5,7,8}
        };


        Board b = new Board(board);
        Board b1 = new Board(n1);
        Board b2 = new Board(n2);
        Board b3 = new Board(n3);
        ArrayList<Board> result = (ArrayList<Board>)b.neighbors();


        assertEquals(3, result.size());
        assertTrue(result.contains(b1));
        assertTrue(result.contains(b2));
        assertTrue(result.contains(b3));

    }

    @Test
    public void testToString() throws Exception {
        int[][] board = {
                {1,2,3},
                {4,0,6},
                {5,7,8}
        };
        int x = Arrays.asList(board).indexOf(5);
        System.out.print('t');

    }
}