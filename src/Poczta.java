import dissimlab.random.RNGenerator;

import java.util.Arrays;

public class Poczta {

    private final double[] windowBusyUntil;
    RNGenerator generator;
    private final double meanServiceRate;

    public Poczta(int N, double meanServiceRate) {
        this.windowBusyUntil = new double[N];
        this.meanServiceRate = meanServiceRate;
        this.generator = new RNGenerator();
        Arrays.fill(windowBusyUntil, Double.NEGATIVE_INFINITY); // Initialize all windows as free.
    }

    public void simulate(double czasZakon, int liczInteres) {
        double simTime = 0; // Current simulation time.
        int servedCustomers = 0; // Number of served customers.
        int turnedAwayCustomers = 0; // Number of turned away customers.
        int total=0;

        while ((servedCustomers + turnedAwayCustomers) <= liczInteres -1) {
            // Losowanie odstępów czasów kolejnych pojawień się interesantów.
            double nextArrival = simTime + generator.exponential(meanServiceRate);

            // If the next arrival is after the end of the simulation time, break the loop.
            if (nextArrival >= czasZakon) {
                break;
            }


            simTime = nextArrival; // Advance simulation time to the next arrival.

            boolean customerServed = false; // Flag to indicate if the customer was served.

            // Iterate over each window to try and serve the customer.
            for (int i = 0; i < windowBusyUntil.length; i++) {
                if (simTime >= windowBusyUntil[i]) { // Check if the window is free.
                    windowBusyUntil[i] = simTime + generator.exponential(1/meanServiceRate); // Set the window as busy.
                    servedCustomers++; // Increment the number of served customers.

                    customerServed = true; // Set the flag as the customer is served.
                    System.out.println("Obluzony w oknie: "+ i);
                }
                else {
                    System.out.println("Window is busy right now...");
                }
            }

            if (!customerServed) { // If no window was free, the customer is turned away.
                turnedAwayCustomers++;

            }

        }


        // Output the simulation results.
        System.out.println("Customers served: " + servedCustomers);
        System.out.println("Customers turned away: " + turnedAwayCustomers);
    }
}

