import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by chad on 2/13/2016.
 */
public class PointTest {

    @Test
    public void testCompareToLess() throws Exception {
        Point p = new Point(1,2);
        assert p.compareTo(new Point(1,3)) == -1;
    }
    @Test
    public void testCompareToGreater() throws Exception {
        Point p = new Point(1,2);
        assert p.compareTo(new Point(1,1)) == 1;
    }
    @Test
    public void testCompareToEqual() throws Exception {
        Point p = new Point(1,2);
        assert p.compareTo(new Point(1,2)) == 0;
    }
    @Test
    public void testCompareToTieLess() throws Exception {
        Point p = new Point(0,2);
        assert p.compareTo(new Point(1,2)) == -1;
    }
    @Test
    public void testCompareToTieGreater() throws Exception {
        Point p = new Point(2,2);
        assert p.compareTo(new Point(1,2)) == 1;
    }
    @Test
    public void testCompareToTieEqual() throws Exception {
        Point p = new Point(2,2);
        assert p.compareTo(new Point(2,2)) == 0;
    }
    @Test
    public void testSlopeHorizontal() throws Exception {
        Point p = new Point(2,2);
        assert p.slopeTo(new Point(4,2)) == 0;
    }
    @Test
    public void testSlopeVertical() throws Exception {
        Point p = new Point(2,2);
        assert p.slopeTo(new Point(2,4)) == 0;
    }
    @Test
    public void testSlopeX() throws Exception {
        Point p = new Point(2,2);
        assert p.slopeTo(new Point(3,4)) == 2.0;
    }
    @Test
    public void testSlopeOrder() throws Exception{

        Point[] points = new Point[5];

        Point a = new Point(1,1);
        Point b = new Point(2,2);
        Point c = new Point(3,3);
        Point d = new Point(2,1);
        Point e = new Point(1,5);

        points[0] = a;
        points[1] = b;
        points[2] = c;
        points[3] = d;
        points[4] = e;

        Arrays.sort(points, b.slopeOrder());

        assert points[0] == b;
        assert points[1] == a;
        assert points[2] == c;
        assert points[3] == e;
        assert points[4] == d;

    }



}