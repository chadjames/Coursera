import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chad on 2/13/2016.
 */
public class BruteCollinearPointsTest {

    @Test
    public void testNumberOfSegments4Points() throws Exception {
        Point[] points = new Point[4];
        for(int i = 0; i < 4; i++){
            points[i] = new Point(3, points.length - 1 - i);
        }
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        assertEquals(1, bcp.numberOfSegments());
        assertEquals("(3, 0) -> (3, 3)", bcp.segments()[0].toString());
    }
    @Test
    public void testNumberOfSegments6Points() throws Exception {
        Point[] points = new Point[6];
        for(int i = 0; i < 6; i++){
            if(i == 2 | i == 4){
                points[i] = new Point(5, i + 1);
            }else{
                points[i] = new Point(3, points.length - 1 - i);
            }
        }
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        assertEquals(1, bcp.numberOfSegments());
        assertEquals("(3, 0) -> (3, 5)", bcp.segments()[0].toString());
    }
    @Test
    public void testNumberOfSegments2LineSegments() throws Exception {
        Point[] points = new Point[8];
        for(int i = 0; i < 8; i++){
            if(i%2 == 0){
                points[i] = new Point(6, points.length - 1 - i);
            }else{
                points[i] = new Point(3, points.length - 1 - i);
            }
        }
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        assertEquals(2, bcp.numberOfSegments());
        assertEquals("(3, 0) -> (3, 6)", bcp.segments()[1].toString());
        assertEquals("(6, 1) -> (6, 7)", bcp.segments()[0].toString());
    }

    @Test
    public void testIsCollinear() throws Exception {
        Point[] points = new Point[5];
        for(int i = 0; i < 5; i++){
            points[i] = new Point(3, points.length - 1 - i);
        }
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
//        assertTrue(bcp.isCollinear(points[0],points[1],points[2],points[3]));

    }
    @Test
    public void testIsCollinearFalse() throws Exception {
        Point[] points = new Point[5];

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
//        assertFalse(bcp.isCollinear(new Point(1,5), new Point(2,3),new Point(4,8),new Point(6,5)));

    }
    @Test
    public void testIsCollinearFail() throws Exception {
        Point[] points = new Point[4];
        points[0] = new Point(3,5);
        points[1] = new Point(3,2);
        points[2] = new Point(3,4);
        points[3] = new Point(5,5);
//        assertFalse(BruteCollinearPoints.isCollinear(points[0],points[1],points[2],points[3]));
    }
    @Test
    public void testIsCollinearFailArrayResize() throws Exception {
        Point[] points = new Point[12];
        int row = 1;
        for (int i = 0; i < 12; i++) {
            if(i != 0 && i%4 == 0){
                row++;
                points[i] = new Point(row, points.length - 1 - i);
            }
            points[i] = new Point(row, points.length - 1 - i);
        }
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        assertEquals(3, bcp.numberOfSegments());
    }
    @Test
    public void testDuplicate() throws Exception{
        Point[] points = new Point[2];
        points[0] = new Point(1,1);
        points[1] = new Point(1,1);
        try{
            BruteCollinearPoints bcp = new BruteCollinearPoints(points);
            fail();
        }catch (Exception e){

        }

    }
}