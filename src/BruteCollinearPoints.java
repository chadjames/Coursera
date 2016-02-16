import java.util.Arrays;

/**
 * Created by chad on 2/13/2016.
 */
public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int count;

    public BruteCollinearPoints(Point[] points) {
        segments = new LineSegment[0];

        validate(points);
        if(points == null){
            throw new NullPointerException();
        }
        count = 0;
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);

        for (int a = 0; a < copy.length - 3; a++) {
            for (int b = a + 1; b < copy.length - 2; b++) {
                for (int c = b + 1; c < copy.length - 1; c++) {
                    for (int d = c + 1; d < copy.length; d++) {
                        if (isCollinear(copy[a], copy[b], copy[c], copy[d])) {
                            addLineSegment(copy[a], copy[d]);
                        }
                    }
                }
            }
        }
    }

    private void validate(Point[] points){
        if(points == null){
            throw new NullPointerException();
        }
        for(int i = 0; i < points.length; i++){
            if(points[i] == null){
                throw new NullPointerException();
            }
            for(int a = i+1; a<points.length; a++){
                if(points[i].compareTo(points[a]) == 0){
                    throw new IllegalArgumentException();
                }
            }
        }

    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private static boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
        return p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4);

    }

    private void addLineSegment(Point a, Point b){
        LineSegment segment = new LineSegment(a,b);
        segments = Arrays.copyOf(segments, count+1);
        segments[count++] = segment;
    }
}
