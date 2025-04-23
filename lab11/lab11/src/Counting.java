/**
 * COunting Class - thread that counts from 1 to 10
 */
public class Counting extends Thread{

    private int thread;

    /**
     * Constructor
     * @param thread thread number used in output message
     */
    public Counting(int thread){
        this.thread = thread;
    }

        /**
         * Override run method, output count from 1 to 10
         */
        @Override
        public void run(){
            for (int i = 1; i < 11; i++){
            System.out.println("Thread #" + thread + " count: " + i);
        }

    }
    
}
