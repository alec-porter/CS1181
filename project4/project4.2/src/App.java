import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Project 4");

        long startTime = System.currentTimeMillis();
		int numThreads = 8;
		int passwordLength = 5;
		String filename = "protected5.zip";

        // verify file to crack exists
        try{
			File file = new File(filename);
            Scanner inputFile = new Scanner(file);
            inputFile.close();
		}catch(FileNotFoundException e){
            System.out.println("File not found, exiting program.");
			System.exit(0);
        }

        // create copies of zip file
		for (int i = 0; i < numThreads; i++){
			Files.copy(Path.of(filename), Path.of("test" + i));
		}

		// run threads
		ArrayList<Thread> threads = new ArrayList<>();
		for (int threadNum = 0; threadNum < numThreads; threadNum++){
			CrackingThread crackThis = new CrackingThread("test" + threadNum, passwordLength, threadNum, numThreads);
			Thread crackingThread = new Thread(crackThis);
			threads.add(crackingThread);
			crackingThread.start();
		}

		// wait for all thread to complete
		for (Thread n : threads){
			n.join();
		}
		
		// delete created files
		for (int j = 0; j < numThreads; j++){
			Files.delete(Path.of("test" + j));
			Files.delete(Path.of("contents" + j + "/message.txt"));
			Files.delete(Path.of("contents" + j));
		}

        // print time to crack
		long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Program Finished in " + totalTime + " ms");

    }
}
