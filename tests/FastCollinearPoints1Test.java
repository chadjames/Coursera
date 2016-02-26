import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cjamec1 on 2/16/2016.
 */
public class FastCollinearPoints1Test {
    @Test
    public void testSegments(){
        Point[] points = new Point[5];
        points[0] = new Point(1,2);
        points[1] = new Point(0,6);
        points[2] = new Point(2,3);
        points[3] = new Point(3,4);
        points[4] = new Point(4,5);

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assert 1 == fcp.segments().length;

    }

    @Test
    public void testConstructor(){
        Point[] points = new Point[8];
        points[0] = new Point(1,1);
        points[1] = new Point(1,2);
        points[2] = new Point(2,2);
        points[3] = new Point(2,3);
        points[4] = new Point(3,3);
        points[5] = new Point(3,4);
        points[6] = new Point(4,4);
        points[7] = new Point(4,5);

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assert 2 == fcp.segments().length;

    }
}

