import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by chad on 1/30/2016.
 */
public class PercolationStats {
    private int[] results;
    private Stopwatch stopwatch;

    public PercolationStats(int N, int T) {
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        results = new int[T];
        stopwatch = new Stopwatch();
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);

            int count = 0;
            while (!percolation.percolates()) {
                int a = StdRandom.uniform(N) + 1;
                int b = StdRandom.uniform(N) + 1;

                if(a>0 && b>0 && !percolation.isOpen(a,b)){
                    percolation.open(a == 0 ? 1 : a, b == 0 ? 1 : b);
                    count++;
                }
            }
            results[i] = count;
        }

    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return (mean() - (1.96)*(Math.sqrt(stddev()))/Math.sqrt(results.length));
    }

    public double confidenceHi() {
        return (mean() + (1.96)*(Math.sqrt(stddev()))/Math.sqrt(results.length));
    }

    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println("mean                    = "  + ps.mean());
        System.out.println("stddev                  = "  + ps.stddev());
        System.out.println("95% confidence interval = "  + ps.confidenceLo() + " , " + ps.confidenceHi());
        System.out.println("time = " + ps.stopwatch.elapsedTime());

    }
}
