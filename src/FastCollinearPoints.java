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

            double previousSlope = 0;
            double currentSlope;
            boolean currentMatchFound;
            List<Point> matchingSlopes = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                currentSlope = point.slopeTo(list.get(i));
                if(previousSlope == 0){
                    previousSlope = currentSlope;
                    matchingSlopes.add(list.get(i));
                    continue;
                }
                if(currentSlope == previousSlope){
                    currentMatchFound = true;
                    matchingSlopes.add(list.get(i));
                }else{
                    currentMatchFound = false;
                    previousSlope = currentSlope;
                }
                if(!currentMatchFound && matchingSlopes.size() >= 2 || i == list.size()-1 && matchingSlopes.size() >= 2 ){
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
