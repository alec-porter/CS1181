/**
 * PrimeThread Class - computes prime numbers
 */
public class PrimeThread extends Thread{

    int numThreads;
    int start;
    int end;

    /**
     * Constructor
     * @param start number to start identifing prime numbers
     * @param end number to end indentifing prime numbers
     */
    public PrimeThread(int start, int end){
        this.start = start; // inclusive
        this.end = end; // exclusive
    }

    /**
     * Determine if a number is prime
     * @param num number to determine if prime
     * @return true/false if number is prime
     */
    public static boolean isPrime(int num){
        if (num <= 1){
            return false;
        } 
        if (num <= 3){
            return true;
        } 
        if (num % 2 == 0 || num % 3 == 0){
            return false;
        } 
        for (int i=5; i*i <= num; i+=6){
            if (num % i == 0 || num % (i+2) == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Count number of prime numbers in number range
     */
    @Override
    public void run(){
        int count = 0;
        for (int n = start; n < end; n++){
            if (isPrime(n)){
                count++;
            }
        }
    }
}
