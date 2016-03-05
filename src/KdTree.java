import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KdTree {
    private Set<Point2D> data = new TreeSet<>();
    public KdTree() {
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public void insert(Point2D p) {
        validate(p);
        data.add(p);
    }

    public boolean contains(Point2D p) {
        validate(p);
        return data.contains(p);
    }

    public void draw() {
        data.forEach(point -> StdDraw.point(point.x(),point.y()));
    }

    public Iterable<Point2D> range(RectHV rect) {
        validate(rect);

        List<Point2D> range = new ArrayList<>();
        for(Point2D point: data ){
            if(rect.contains(point)){
                range.add(point);
            }
        }
        return range;
    }

    public Point2D nearest(Point2D p) {
        validate(p);
        if(data.isEmpty()){
            return null;
        }
        Point2D closest = null;
        for(Point2D point: data ){
            if(closest == null || point.distanceTo(p) < point.distanceTo(closest)){
                closest = point;
            }
        }
        return closest;
    }

    private void validate(Object o){
        if(o == null){
            throw new NullPointerException("Arg can not be null");
        }
    }

    public static void main(String[] args) {
    }
}
