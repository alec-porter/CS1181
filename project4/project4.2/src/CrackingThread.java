import java.util.ArrayList;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.exception.*;

public class CrackingThread implements Runnable{

    private ArrayList<String> dictionary = new ArrayList<>();
    private String filename;
    private int passwordLength;
    private int threadNum;
    private int numThreads;
    volatile static boolean endThread = false;

    public CrackingThread(String filename, int passwordLength, int threadNum, int numThreads){
        this.filename = filename;
        this.passwordLength = passwordLength;
        this.threadNum = threadNum;
        this.numThreads = numThreads;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();  // start time
        char[] alphabet = new char[26];
        for (int i = 0; i < 26; i++){
            alphabet[i] = (char)(i + 97);
        }

        // create all three character passwords from lowercase alphabet
        if (passwordLength == 3){
            for (char a : alphabet){
                for (char b : alphabet){
                    for (char c : alphabet){
                        dictionary.add(Character.toString(a) + Character.toString(b) + Character.toString(c));
                    }
                }
            }
        }

        // create all five character passwords from uppercase alphabet
        if (passwordLength == 5){
            for (char a : alphabet){
                for (char b : alphabet){
                    for (char c : alphabet){
                        for (char d : alphabet){
                            for (char e : alphabet){
                                dictionary.add(Character.toString(a) + Character.toString(b) + Character.toString(c) + Character.toString(d) + Character.toString(e));
                            }
                        }
                    }
                }
            }
        }

        int count = 0;
        String password;

        while(!endThread){

            if ((numThreads * count + threadNum) > (dictionary.size()-1)){
                endThread = true;
            }

            else{
                password = dictionary.get(numThreads * count + threadNum);
                try { // matching password on zip file found
                    ZipFile zipFile = new ZipFile(filename);
                    zipFile.setPassword(password);
                    zipFile.extractAll("contents" + threadNum);
                    System.out.println("Successfully cracked! Password: " + password);
                    endThread = true; // end threads
                    count++;
                } catch (ZipException ze) {
                    count++;
                } catch (Exception e){
                    e.printStackTrace();
                    count++;
                } 
            }

        }

        long finishTime = System.currentTimeMillis(); 
        long totalTime = finishTime - startTime;
        System.out.println("Thread-" + threadNum +" Finished in " + totalTime + " ms");

    }
    
}
