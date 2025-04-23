import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        System.out.println("Alec Porter Project 4");
		/**
		 * Passwords: may, iamin
		 * 
		 * Cracking Times
		 * 
		 * 1 Thread on protected3.zip -  1313ms
		 * 3 Threads on protected3.zip - 1104ms
		 * 4 Threads on protected3.zip - 911ms
		 * 8 Threads on protected3.zip - 757ms
		 * 1 Thread on protected5.zip -  483699ms
		 * 3 Threads on protected5.zip - 155500ms
		 * 4 Threads on protected5.zip - 121032ms
		 * 8 Threads on protected5.zip - 72185ms
		 */

		// initialize variables
		long startTime = System.currentTimeMillis();
		int numThreads = 3;
		int passwordLength = 3;
		String filename = "protected3.zip";

		// verify file to crack exists
        try{
			File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.close();
		}catch(FileNotFoundException e){
            System.out.println("File not found, exiting program.");
			System.exit(0);
        }

		// create dictionaries
		Dictionary newDictionary = new Dictionary(passwordLength, numThreads);
		newDictionary.createDictionaries();

		// create copies of zip file
		for (int i = 0; i < numThreads; i++){
			Files.copy(Path.of(filename), Path.of("test" + i));
		}

		// run threads
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < numThreads; i++){
			CrackingThread crackThis = new CrackingThread("dictionary" + i, "test" + i, i);
			Thread crackingThread = new Thread(crackThis);
			
			crackingThread.start();
			threads.add(crackingThread);
		}

		// wait for all threads to complete
		for (Thread n : threads){
			n.join();
		}
		
		//delete created files
		for (int j = 0; j < numThreads; j++){
			Files.delete(Path.of("test" + j));
			Files.delete(Path.of("contents" + j + "/message.txt"));
			Files.delete(Path.of("contents" + j));
		}
		newDictionary.deleteDictionaries();
	
		
		// print time to crack
		long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Program Finished in " + totalTime + " ms");

    }

}
