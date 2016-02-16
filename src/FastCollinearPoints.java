import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chad on 2/14/2016.
 */
public class FastCollinearPoints {
    private List<LineSegment> segments;
    public FastCollinearPoints(Point[] points) {
        segments = new ArrayList<>();
        List<Point> list = Arrays.asList(Arrays.copyOf(points, points.length));

        for (Point point : list) {
            Collections.sort(list, point.slopeOrder());

            for (int i = 1; i < list.size(); i++) {
                double slopeTo = point.slopeTo(list.get(i));

                List<Point> matchingSlopes = list.stream()
                        .filter(p -> point.slopeTo(p) == slopeTo)
                        .collect(Collectors.toList());

                if (matchingSlopes.size() >= 3) {
                    matchingSlopes.add(point);
                    addSegment(matchingSlopes);
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
        for(LineSegment foundSegment: segments){
            if(foundSegment.toString().equals(segment.toString())){
                existing = true;
            }
        }
        if(!existing){
            segments.add(segment);
        }
    }
}
