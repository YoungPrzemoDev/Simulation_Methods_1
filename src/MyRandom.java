import java.util.Arrays;
import java.util.Collections;

public class MyRandom {

    private long x0;
    private final int a;
    private final int b;
    private final int M;

    public MyRandom(long seed, int a, int b, int M) {
        this.x0 = seed;
        this.a = a;
        this.b = b;
        this.M = M;
    }

    public int nextInt() {
        return (int) (x0 =  (a * x0 + b) % M);
    }

    public double nextDouble() {
        return ((double) nextInt()) / M;
    }

    public double nextDouble(double low, double high) {
        return low + (high - low) * nextDouble();
    }

    public double exponential(double lambda) {
        return -Math.log(nextDouble()) / lambda;
    }

    public double dyskret(double[] xx, double[] p) {
        if (xx.length != p.length) throw new IllegalArgumentException("Arrays must be of the same length");
        double sum = 0;
        for (double pi : p) {
            sum += pi;
        }
        if (Math.abs(sum - 1.0) != 0) throw new IllegalArgumentException("Probabilities must sum up to 1");

        double u = nextDouble();

        double cumulativeProbability = 0;
        for (int i = 0; i < p.length; i++) {
            cumulativeProbability += p[i];
            if (u < cumulativeProbability) {
                return xx[i];
            }
        }
        // Teoretycznie nigdy tu nie dotrzemy
        throw new RuntimeException("Should not get here.");
    }
}
