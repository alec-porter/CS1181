import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 11");

        /**
         * n = 10000000, threads = 1: 1538 ms
         * n = 10000000, threads = 2: 982 ms
         * n = 10000000, threads = 3: 694 ms
         * n = 10000000, threads = 4: 541 ms
         */

        // initialize
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        int numThreads = 4;
        int n = 10000000;
        int start;
        int end;
        int div = n / numThreads;

        // loop through each thread
        for (int i = 0; i < numThreads; i++){
            start = (i * div) + 1; // create start point
            if (i == (numThreads - 1) ){
                end  = n; // last loop end at n to ensure all numbers are checked
            }
            else {
                end = (div * (i + 1)); // create end point
            }
            Thread thread = new PrimeThread(start, end); // count primes bewteen start (inclusive) and end (exclusive)
            threads.add(thread);
            thread.start();
        }

        // combine threads
        for (Thread x : threads){
            x.join();
        }

        // calculate and dispaly time to complete
        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Program Finished in " + totalTime + " ms");

    }
}
