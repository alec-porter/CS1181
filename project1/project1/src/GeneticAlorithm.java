import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

/**
 * Genetic Algorithm - CS1181 
 * 
 * @author Alec Porter
 */
public class GeneticAlorithm {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Alec Porter Project 1\n");

        // initialize algorithm
        ArrayList<Chromosome> nextGen = new ArrayList<>(); 
        ArrayList<Chromosome> currentPopulation = new ArrayList<>();
        ArrayList<Item> items;

        // Set values
        int algorithmIterations = 20;
        int popluationSize = 10;
        String fileName = "Items.txt";

        // limit total size of next generation to reduce execution time and memory required, useful if running long item list with a lot of iterations 
        boolean limit = false;
        int limitAmount = 5000;

        items = readData(fileName); // create items arraylist

        // Step 1 - Create a random set of individuals as the initial population
        currentPopulation = initializePopulation(items, popluationSize);  // initialize population
        
        // genetic algorithm
        for (int n = 0; n < algorithmIterations; n++){  // iterate through initialized number of iterations

            // Step 2 - Add current population to next generation
            for(Chromosome m : currentPopulation){
                Chromosome copy = new Chromosome();  
                for (Item x : m){  // add items to chromosome and copy to next generation
                    copy.add(new Item(x));
                }
                nextGen.add(copy);
            }

            // Step 3 - Pair off parents and genterate children
            ArrayList<Integer> parent = new ArrayList<>();
            for (int p = 0; p < currentPopulation.size(); p++){  // create arraylist of integers
                parent.add(p); 
            }
            Collections.shuffle(parent);  // shuffle arralylist of integers
            for (int c = 0; c < currentPopulation.size()/2; c++){  
                nextGen.add(currentPopulation.get(2*c).crossover(currentPopulation.get(2*c+1)));  // create children from parents
            }

            // Step 4 - Randomly choose 10% of the next generation and mutate
            int numOfMutations = (int)Math.round((double)nextGen.size()/10);  // determine mutation size by rounding double value and typecasting to integer
            ArrayList<Integer> mutations = new ArrayList<>();
            for (int m = 0; m < nextGen.size(); m++){  // create arralylist of integers for mutation
                mutations.add(m);
            }
            Collections.shuffle(mutations);  // shuffle arraylist of integers for mutation, ensures the same individual isn't mutated more than once
            for (int m = 0; m < numOfMutations; m++){
                nextGen.get(mutations.get(m)).mutate();
            }

            // Step 5 - Sort individuals in next generation according to fitness
            Collections.sort(nextGen);

            // Step 6 - Clear out current populaton and add top 10 of the next generation
            currentPopulation.clear();
            int count = 0;
            for(Chromosome m : nextGen){
                Chromosome copy = new Chromosome();
                for(Item x : m){
                    copy.add(new Item(x));
                }
                if (count < 10){
                    currentPopulation.add(copy);
                }
                count++;
            }

            // Limit size of nextGen
            if (limit){
                for (int q = limitAmount; q < nextGen.size(); q++){
                    nextGen.remove(nextGen.size()-1);
                }
            }


        }
        // Step 9 - sort population and display fittest individual
        Collections.sort(currentPopulation);
        System.out.println(currentPopulation.get(0));
    }


// ----- METHODS ----- //

/**
 * Reads in a data file with the format below and creates and returns an ArrayList of Item objects
 * 
 * file format:
 * item1_label, item1_weight, item1_value
 * item2_label, item2_weight, item2_value
 * ...
 * 
 * @param filename data filename 
 * @return Item object generated from input file
 * @throws FileNotFoundException notifies user if filename is invalid
 */
public static ArrayList<Item> readData(String filename) throws FileNotFoundException{

    ArrayList<Item> items = new ArrayList<>();

        try {
            File fileHandle = new File(filename);  // open file
            Scanner inputFile = new Scanner(fileHandle);
            while(inputFile.hasNextLine()){
                // read in line from file and parse data
                String itemData = inputFile.nextLine();
                String[] itemDataSplit = itemData.split(", ", 3);
                String itemName = itemDataSplit[0];
                double itemWeight = Double.parseDouble(itemDataSplit[1]);
                int itemValue = Integer.parseInt(itemDataSplit[2]);
                items.add(new Item(itemName, itemWeight, itemValue));  // add item to arraylist
            }     
            inputFile.close();    
        } 
        catch (FileNotFoundException e) {
            System.out.println("Not a valid file name!");
            System.exit(0);
        }

    return items;
}

/**
 * Creates and returns an ArrayList of populationSize Chromosome objects that each contain the items, with their included field randomly set to true or false.
 * 
 * @param items Item object generated from input file
 * @param populationSize number of Chromosome objects to generate
 * @return Chromosome objects
 */
public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize){

    ArrayList<Chromosome> chromosomes = new ArrayList<>();
    
    for (int i = 0; i < populationSize; i++){
        ArrayList<Item> copy = new ArrayList<>(); // new arraylist to store deep copy
        for(Item n: items){  // iterate through original arraylist
            copy.add(new Item(n));  // create a new copy of each element
        }
        chromosomes.add(new Chromosome(copy));
    }
    return chromosomes;
}

/**
 * Human friendly printout of Chromosomes input
 * 
 * @param chromosomes Chromosome arraylist to print
 */
public static void printChromosomeList(ArrayList<Chromosome> chromosomes){
    for (Chromosome n : chromosomes){
        System.out.println(n);
    }
    System.out.println();
}

}
