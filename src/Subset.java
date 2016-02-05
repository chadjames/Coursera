import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Subset {
    public static void main(String[] args){
        RandomizedQueue<String> in = new RandomizedQueue<>();
        int times = Integer.parseInt(args[0]);

        String[] x = StdIn.readLine().split(" ");
        for(int i = 0; i < x.length; i++){
            in.enqueue(x[i]);
        }

        Iterator<String> it = in.iterator();
        for(int i = 0; i < times; i++){
            System.out.println(it.next());
        }
    }
}