import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chad on 1/29/2016.
 */
public class PercolationTest {
    Percolation percolation;

    @Before
    public void setup() throws Exception {


    }

    @Test
    public void testConstructor() throws Exception {
        percolation = new Percolation(10);
//        assertTrue(10 == percolation.getSize());

    }

    @Test
    public void testIsOpen() throws Exception {
        percolation = new Percolation(10);
        assertFalse(percolation.isOpen(0, 0));

    }

    @Test
    public void testOpen() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
    }

    @Test
    public void testOpenConnectsAdjecentSites() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 1);
        percolation.open(1, 2);
//        assertTrue(percolation.getQU().connected(0, 1));
    }

    @Test
    public void testOpenConnectsAdjacentSitesAdjacentBefore() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);

//        assertTrue(percolation.getQU().connected(0, 10));
//        assertTrue(percolation.getQU().connected(10, 20));
//        assertTrue(percolation.getQU().connected(0, 20));

    }


    @Test
    public void testOpenConnectsAdjacentSitesAfter() throws Exception {
        percolation = new Percolation(10);
        percolation.open(3, 1);
        percolation.open(2, 1);
        percolation.open(1, 1);

//        assertTrue(percolation.getQU().connected(0, 10));
//        assertTrue(percolation.getQU().connected(20, 10));
//        assertTrue(percolation.getQU().connected(0, 20));
    }

    @Test
    public void testOpenConnectsAdjacentSitesLeft() throws Exception {
        percolation = new Percolation(10);
        percolation.open(2, 1);
        percolation.open(2, 2);

//        assertTrue(percolation.getQU().connected(10, 11));

    }

    @Test
    public void testOpenConnectsAdjacentSitesAdjacentLeftMultiple() throws Exception {
        percolation = new Percolation(10);
        percolation.open(2, 1);
        percolation.open(2, 2);
        percolation.open(2, 3);

//        assertTrue(percolation.getQU().connected(10, 11));
//        assertTrue(percolation.getQU().connected(11, 12));
//        assertTrue(percolation.getQU().connected(10, 12));


    }

    @Test
    public void testOpenConnectsAdjacentSitesAdjacentRight() throws Exception {
        percolation = new Percolation(10);
        percolation.open(2, 2);
        percolation.open(2, 1);

//        assertTrue(percolation.getQU().connected(11, 10));

    }

    @Test
    public void testOpenConnectsAdjacentSitesAdjacentMultiple() throws Exception {
        percolation = new Percolation(10);
        percolation.open(2, 3);
        percolation.open(2, 2);
        percolation.open(2, 1);

//        assertTrue(percolation.getQU().connected(10, 11));
//        assertTrue(percolation.getQU().connected(11, 12));
//        assertTrue(percolation.getQU().connected(10, 12));

    }

    @Test
    public void testOpenConnectsVirtualTop() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 2);

//        assertTrue(percolation.getQU().connected(1, 101));

    }
    @Test
    public void testOpenConnectsVirtualTopMultiple() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 2);
        percolation.open(1, 7);

//        assertTrue(percolation.getQU().connected(1, 101));
//        assertTrue(percolation.getQU().connected(6, 101));
//        assertTrue(percolation.getQU().connected(1, 6));

    }
    @Test
    public void testOpenConnectsVirtualBottom() throws Exception {
        percolation = new Percolation(10);
        percolation.open(10, 2);

//        assertTrue(percolation.getQU().connected(91, 100));

    }
    @Test
    public void testOpenConnectsVirtualBottomSmall() throws Exception {
        percolation = new Percolation(2);
        percolation.open(2, 1);

//        assertTrue(percolation.getQU().connected(2, 4));

    }
    @Test
    public void testOpenConnectsVirtualTopSmall() throws Exception {
        percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(2, 1);


//        assertTrue(percolation.getQU().connected(0, 5));
//        assertTrue(percolation.getQU().connected(2, 4));
//        assertTrue(percolation.getQU().connected(5, 4));

    }
    @Test
    public void testOpenConnectsVirtualBottomMultiple() throws Exception {
        percolation = new Percolation(10);
        percolation.open(10, 2);
        percolation.open(10, 5);

//        assertTrue(percolation.getQU().connected(91, 100));
//        assertTrue(percolation.getQU().connected(94, 100));

    }



    @Test
    public void testIsFull() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 2);
        assertTrue(percolation.isFull(1,2));

    }
    @Test
    public void testIsNotFull() throws Exception {
        percolation = new Percolation(10);
        percolation.open(2, 3);
        assertFalse(percolation.isFull(2,3));

    }


    @Test
    public void testPercolates() throws Exception {
        percolation = new Percolation(2);
        percolation.open(1, 1);
        percolation.open(2, 1);
        assertTrue(percolation.percolates());

    }

    @Test
    public void testPercolates1() throws Exception {
        percolation = new Percolation(10);
        percolation.open(1, 2);
        assertTrue(percolation.isFull(1,2));
        percolation.open(2, 2);
        assertTrue(percolation.isFull(2,2));
        percolation.open(2, 3);
        assertTrue(percolation.isFull(2,3));
        percolation.open(3, 3);
        assertTrue(percolation.isFull(3,3));
        percolation.open(3, 4);
        assertTrue(percolation.isFull(3,4));
        percolation.open(4, 4);
        assertTrue(percolation.isFull(4,4));
        percolation.open(5, 4);
        assertTrue(percolation.isFull(5,4));
        percolation.open(5, 5);
        assertTrue(percolation.isFull(5,5));
        percolation.open(6, 5);
        assertTrue(percolation.isFull(6,5));
        percolation.open(6, 6);
        assertTrue(percolation.isFull(6,6));
        percolation.open(7, 6);
        assertTrue(percolation.isFull(7,6));
        percolation.open(7, 7);
        assertTrue(percolation.isFull(7,7));
        percolation.open(8, 7);
        assertTrue(percolation.isFull(8,7));
        percolation.open(8, 8);
        assertTrue(percolation.isFull(8,8));
        percolation.open(9, 8);
        assertTrue(percolation.isFull(9,8));
        percolation.open(9, 9);
        assertTrue(percolation.isFull(9,9));
        percolation.open(10, 9);
        assertTrue(percolation.isFull(1,2));
        assertTrue(percolation.percolates());

    }

    @Test
    public void testPercolates2() throws Exception {
        percolation = new Percolation(3);
        percolation.open(1, 2);
        assertTrue(percolation.isFull(1,2));
        percolation.open(2, 2);
        assertTrue(percolation.isFull(2,2));

        percolation.open(3, 3);
        assertFalse(percolation.isFull(3,3));

        percolation.open(2, 3);
        assertTrue(percolation.isFull(2,3));

        assertTrue(percolation.isFull(3,3));



    }

    @Test
    public void testFullWhenAttachingOnLeft() throws Exception {
        percolation = new Percolation(3);
        percolation.open(1, 3);
        assertTrue(percolation.isFull(1,3));
        percolation.open(2, 3);
        assertTrue(percolation.isFull(2,3));

        percolation.open(2, 2);
        assertTrue(percolation.isFull(2,2));

        percolation.open(2, 1);
        assertTrue(percolation.isFull(2,1));




    }


}