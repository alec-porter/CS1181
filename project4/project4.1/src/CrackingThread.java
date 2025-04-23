import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

/**
 * CrackingThread - attempts to crack pasword protected zip file using a dicitonary based brute force attack
 * 
 * @author Alec Porter
 */
public class CrackingThread implements Runnable {

    // initialize variables
    private String dictionary;
    private String filename;
    private int threadNum;
    volatile static boolean endThread;

    /**
     * CrackingThread Constructor
     * @param dictionary dictionay title
     * @param filename zip file title
     * @param threadNum thread number to attempt cracking
     */
    public CrackingThread(String dictionary, String filename, int threadNum) {
        this.dictionary = dictionary;
        this.filename = filename;
        this.threadNum = threadNum;
    }

    /**
     * Override run method of runnable interface
     */
    @Override
    public void run(){

        long startTime = System.currentTimeMillis();  // start time
        String password;

        try {

            File openFile = new File(dictionary); 
            Scanner inputFile = new Scanner(openFile); // open thread's dictionary

            while(inputFile.hasNext() && !endThread){ // iterate through dictioanary

                password = inputFile.nextLine(); // read password from dictionary

                try { // matching password on zip file found
                    ZipFile zipFile = new ZipFile(filename);
                    zipFile.setPassword(password);
                    zipFile.extractAll("contents" + threadNum); // create unique extraction folder for each thread
                    System.out.println("Successfully cracked! Password: " + password);
                    endThread = true; // end threads
                } catch (ZipException ze) { // no action taken if password doesn't open zip file
                } catch (Exception e){
                    e.printStackTrace();
                } 
            }

            inputFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // print total time thread ran until password cracked or thread ended
        long finishTime = System.currentTimeMillis(); 
        long totalTime = finishTime - startTime;
        System.out.println("Thread-" + threadNum +" Finished in " + totalTime + " ms");

    }
}
