import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.stream.Collectors;

/**
 * Created by chad on 2/14/2016.
 */
public class FastCollinearPoints {
    private List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        segments = new ArrayList<>();
        List<Point> list = Arrays.asList(Arrays.copyOf(points, points.length));

        for (Point point : points) {
            Collections.sort(list, point.slopeOrder());

            double previousSlope = Double.NaN;
            double currentSlope;
            List<Point> matchingSlopes = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                currentSlope = point.slopeTo(list.get(i));
                if (Double.isNaN(previousSlope)) {
                    previousSlope = currentSlope;
                    matchingSlopes.add(list.get(i));
                    continue;
                }
                if (currentSlope == previousSlope) {
                    matchingSlopes.add(list.get(i));
                }
                if (currentSlope != previousSlope && matchingSlopes.size() < 3) {
                    matchingSlopes.clear();
                    matchingSlopes.add(list.get(i));
                    previousSlope = currentSlope;
                    continue;
                }
                if (currentSlope != previousSlope && matchingSlopes.size() >= 3 || (matchingSlopes.size() >= 3 && i == list.size() - 1)) {
                    matchingSlopes.add(point);
                    addSegment(matchingSlopes);
                    matchingSlopes.clear();
                    break;

                }
            }
        }

    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] convertedList = new LineSegment[segments.size()];
        return segments.toArray(convertedList);
    }

    private void addSegment(List<Point> points) {
        Collections.sort(points);
        LineSegment segment = new LineSegment(points.get(0), points.get(points.size() - 1));
        boolean existing = false;
        for (LineSegment foundSegment : segments) {
            if (foundSegment.toString().equals(segment.toString())) {
                existing = true;
            }
        }
        if (!existing) {
            segments.add(segment);
        }
    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();

        }
    }
}
