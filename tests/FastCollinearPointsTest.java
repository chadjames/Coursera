import org.junit.Test;

/**
 * Created by chad on 2/14/2016.
 */
public class FastCollinearPointsTest {
    @Test
    public void testStuff(){
        Point p = new Point(1,1);
        Point[] points = new Point[6];
        points[0] = new Point(1,1);
        points[1] = new Point(2,2);
        points[2] = new Point(3,3);
        points[3] = new Point(4,4);
        points[4] = new Point(4,6);
        points[5] = new Point(4,8);

        FastCollinearPoints fcp = new FastCollinearPoints(points);
    }
    @Test
    public void testSegments(){
        Point[] points = new Point[4];
        points[0] = new Point(1,1);
        points[1] = new Point(2,2);
        points[2] = new Point(3,3);
        points[3] = new Point(4,4);

        FastCollinearPoints fcp = new FastCollinearPoints(points);
        assert 1 == fcp.segments().length;

    }


}