import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by chad on 3/5/2016.
 */
public class PointSETTest {
    PointSET ps;
    @Before
    public void setUp(){
        ps = new PointSET();
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(ps.isEmpty());
    }
    @Test
    public void testIsNotEmpty() throws Exception {
        ps.insert(new Point2D(1,1));
        assertFalse(ps.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0,ps.size());
    }
    @Test
    public void testSize1() throws Exception {
        ps.insert(new Point2D(1,1));
        assertEquals(1,ps.size());
    }

    @Test
    public void testInsert() throws Exception {
    }

    @Test
    public void testContains() throws Exception {
        Point2D p = new Point2D(1,1);
        ps.insert(p);
        assertTrue(ps.contains(p));
    }

    @Test
    public void testDoesNotContainDuplicates() throws Exception {
        Point2D p = new Point2D(1,1);
        ps.insert(p);
        ps.insert(p);
        assertEquals(1,ps.size());
    }

    @Test
    public void testRange() throws Exception {
        RectHV rect = new RectHV(0,0,5,5);
        Point2D p = new Point2D(1,1);
        Point2D p1 = new Point2D(2,2);

        ps.insert(p);
        ps.insert(p1);
        ArrayList<Point2D> result = (ArrayList<Point2D>)ps.range(rect);
        assertEquals(2,result.size());
    }
    @Test
    public void testRangeOutside() throws Exception {
        RectHV rect = new RectHV(0,0,5,5);
        Point2D p = new Point2D(1,1);
        Point2D p1 = new Point2D(2,7);

        ps.insert(p);
        ps.insert(p1);
        ArrayList<Point2D> result = (ArrayList<Point2D>)ps.range(rect);
        assertEquals(1,result.size());
    }

    @Test
    public void testNearest() throws Exception {
        Point2D p = new Point2D(1,1);
        Point2D p1 = new Point2D(2,7);
        Point2D p2 = new Point2D(2,2);

        ps.insert(p1);
        ps.insert(p2);

        assertSame(p2, ps.nearest(p));

    }

    @Test
    public void testNullPointer(){
        try{
            ps.insert(null);
            fail();
        }catch (NullPointerException e){
            assertEquals("Arg can not be null", e.getMessage());
        }
    }


}