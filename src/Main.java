import dissimlab.simcore.SimSpace;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int low = 5;
        int high = 10;
        int lambda = 1;
        int numValues = 10000;
        int M = 100;

        double sumInt = 0;
        double sumDouble = 0;
        double sumDoubleRange = 0;
        double sumExponential = 0;


        //ZAD 1
        MyRandom random = new MyRandom(123456,23,11,M); // Seed for the random number generator

        for (int i = 0; i < numValues; i++) {
            sumInt += random.nextInt();
            sumDouble += random.nextDouble();
            sumDoubleRange += random.nextDouble(low, high);
            sumExponential += random.exponential(lambda);
        }

        double empiricalMeanInt = sumInt / numValues;
        double empiricalMeanDouble = sumDouble / numValues;
        double empiricalMeanDoubleRange = sumDoubleRange / numValues;
        double empiricalMeanExponential = sumExponential / numValues;

        // The theoretical means need to be calculated based on the expected distributions
        double theoreticalMeanInt = (M - 1) / 2.0;
        double theoreticalMeanDouble = 0.5; // For a uniform distribution between 0 and 1
        double theoreticalMeanDoubleRange = ((double) (high + low) / 2); // For a uniform distribution between 5 and 10
        // For an exponential distribution with lambda = 1, the mean is 1/lambda = 1
        double theoreticalMeanExponential = 1;

        // Calculate the relative errors
        double relativeErrorInt = Math.abs((empiricalMeanInt - theoreticalMeanInt) / theoreticalMeanInt);
        double relativeErrorDouble = Math.abs((empiricalMeanDouble - theoreticalMeanDouble) / theoreticalMeanDouble);
        double relativeErrorDoubleRange = Math.abs((empiricalMeanDoubleRange - theoreticalMeanDoubleRange) / theoreticalMeanDoubleRange);
        double relativeErrorExponential = Math.abs((empiricalMeanExponential - theoreticalMeanExponential) / theoreticalMeanExponential);



        System.out.println("\nZAD 1");
        System.out.println("=====================");
        System.out.println("Random int: " + random.nextInt());
        System.out.println("Empirical mean for nextInt(): " + empiricalMeanInt);
        System.out.println("Theoretical mean for nextInt(): " + theoreticalMeanInt);
        System.out.println("Relative error for nextInt(): " + relativeErrorInt);

        System.out.println("\nRandom double [0,1): " + random.nextDouble());
        System.out.println("Empirical mean for nextDouble(): " + empiricalMeanDouble);
        System.out.println("Theoretical mean for nextDouble(): " + theoreticalMeanDouble);
        System.out.println("Relative error for nextDouble(): " + relativeErrorDouble);

        System.out.println("\nRandom double [" + low + "," + high + "): " + random.nextDouble(5, 10));
        System.out.println("Empirical mean for nextDouble(" + low + "," + high + "): " + empiricalMeanDoubleRange);
        System.out.println("Theoretical mean for nextDouble(5, 10): " + theoreticalMeanDoubleRange);
        System.out.println("Relative error for nextDouble(5, 10): " + relativeErrorDoubleRange);

        System.out.println("\nRandom exponential (lambda=1): " + random.exponential(1));
        System.out.println("Empirical mean for exponential(1): " + empiricalMeanExponential);
        System.out.println("Theoretical mean for exponential(1): " + theoreticalMeanExponential);
        System.out.println("Relative error for exponential(1): " + relativeErrorExponential);


        //------------ZAD 2------------------

        System.out.println("\nZAD 2");
        System.out.println("=====================");
        double[] xx = {-2, -1, 0, 1, 2, 3, 4};
        double[] p = {0.2, 0.1, 0.2, 0.1, 0.2, 0.1, 0.1};

        // Sprawdzenie, czy prawdopodobieństwa sumują się do 1
        double sum = 0;
        for (double pi : p) {
            sum += pi;
        }
        System.out.println("Sum of probabilities: " + sum);

        // Generowanie wartości i obliczanie średniej
        double sumOfValues = 0;
        int n = 10000;
        for (int i = 0; i < n; i++) {
            double value = random.dyskret(xx, p);
            sumOfValues += value;
        }
        double empiricalMean = sumOfValues / n;

        // Teoretyczna średnia
        double theoreticalMean = 0;
        for (int i = 0; i < xx.length; i++) {
            theoreticalMean += xx[i] * p[i];
        }

        // Obliczenie i wyświetlenie błędu względnego
        double relativeError = Math.abs((empiricalMean - theoreticalMean) / theoreticalMean);
        System.out.println("Empirical mean: " + empiricalMean);
        System.out.println("Theoretical mean: " + theoreticalMean);
        System.out.println("Relative error: " + relativeError);


        //------------ZAD 3------------------

        System.out.println("\nZAD 3");
        System.out.println("=====================");

        double a = 1;
        double b = Math.E; // assuming b is e as given in the problem
        int rep = 100000000; // number of subdivisions for approximation
        IFunc funkcja1 = new Funkcja1();
        IFunc funkcja2 = new Funkcja2();

        Calka calka = new Calka();

        double wynik1 = calka.calculate(a, b, funkcja1, rep); // całka funkcji x^2 od 0 do 1
        double wynik2 = calka.calculate(a, 5, funkcja2, rep); // całka funkcji sin(x) od 0 do PI

        double actualValue = 3 * (Math.log(b) - Math.log(a)); // analytical solution of the integral
        double error = calka.calculateError(actualValue, wynik1);


        System.out.println("Approximate value of the integral: " + wynik1);
        System.out.println("Actual value of the integral: " + actualValue);
        System.out.println("Relative error of the approximation: " + error);

        double actualValue2 = (Math.pow(5, 3) - Math.pow(a, 3)) / 3; // analytical solution of the integral of x^2
        double error2 = calka.calculateError(actualValue2, wynik2);

        System.out.println("\nApproximate value of the integral: " + wynik2);
        System.out.println("Actual value of the integral: " + actualValue2);
        System.out.println("Relative error of the approximation: " + error2);

        //------------ZAD 4------------------

        System.out.println("\nZAD 4");
        System.out.println("=====================");

        int numberOfWindows = 4; // liczba okienek
        double meanServiceRate =2.0; // średni czas obsługi klienta
        int liczInteres = 23; // liczba interesantów do obsłużenia
        double czasZakon = 250.00; // czas zakończenia symulacji

        Poczta poczta = new Poczta(numberOfWindows, meanServiceRate);
        poczta.simulate(czasZakon, liczInteres);

    }


}