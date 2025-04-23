import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Dictionary - creats dictionaries to use in brute force password cracking of 3 or 5 character lowercase passwords
 */
public class Dictionary{

    // initialize variables
    private ArrayList<String> dictionary = new ArrayList<>();
    private int asciiStart;
    private int passwordLength;
    private int numThreads;

    /**
     * Dictionary Constructor - creates dictionaries based on password length and number of threads
     * @param passwordLength number of lowercase letters in the password
     * @param numThreads number of threads that will be running
     */
    public Dictionary(int passwordLength, int numThreads){
        this.passwordLength = passwordLength;
        this.numThreads = numThreads;
        this.asciiStart = 97;
    }

    /**
     * Creates a dictonary for each thread based on the password character length
     * @throws IOException exception to handle writing to files
     */
    public void createDictionaries() throws IOException{

        // create alphabet of lower case characters 
        char[] alphabet = new char[26];
        for (int i = 0; i < 26; i++){
            alphabet[i] = (char)(i + asciiStart);
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

        // create each thread's dictionary
        for (int i = 0; i < numThreads; i++){
            PrintWriter output = new PrintWriter("dictionary" + i); // create dicitonary file
            boolean writeData = true;
            int count = 0;
            while (writeData){
                try{
                    int index = numThreads * count + i; // write data to file based on calculated incremented index of master dictionary
                    output.println(dictionary.get(index));
                    count++;
                }
                catch(Exception e){ // end loop when the end of the master dictionary is reached
                    writeData = false;
                }
            }
            output.close();
        }

    }

    /**
     * Deletes created dictionaries 
     * @throws IOException exception to handle deleting files
     */
    public void deleteDictionaries() throws IOException{

        for (int i = 0; i < numThreads; i++){ // delete each thread's dictionary
            Files.delete(Path.of("dictionary" + i));
        }
    }

}
