
interface IFunc{
    public double func(double x);
    public double max(double a, double b);
}

class Funkcja1 implements IFunc{
    public double func(double x){
        return 3/x;
    }
    public double max(double a, double b){
        return Math.max(a,b);
    }
}
class Funkcja2 implements IFunc{
    public double func(double x){
        return x*x;
    }
    public double max(double a, double b){
        return 1;
    }
}
public class Calka{

    double calculate(double a, double b, IFunc f, int rep){
        double result = 0.0;
        double step = (b - a) / rep;
        for (int i = 0; i < rep; i++) {
            result += f.func(a + i * step) * step;
        }
        return result;
    }

    double calculateError(double actualValue, double approxValue) {
        return Math.abs((actualValue - approxValue) / actualValue);
    }

}
