import java.util.ArrayList;
import java.util.Random;

/**
 * Chromosome Class
 * 
 * @author Alec Porter
 */
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    private static Random rng;  

    /**
     * No argument constructor
     */
    public Chromosome(){
    }

    /**
     * Adds a copy of each of the items passed in to this chromosome.  
     * Uses a random number to decide whether each item's included field is set to true or false.
     * 
     * @param items arraylist of items to add to this chromosome
     */
    public Chromosome(ArrayList<Item> items){

        rng = new Random();
        for (Item n : items){ // loop through arralylist
            int rand = rng.nextInt(2);
            if (rand == 0 ){  // change boolean to true if random number is 0
                n.setIncluded(true);
                this.add(n); // add element to chromosome
            }
            else{
                this.add(n); // add element to chromosome
            }
        }
    }

    /**
     * Creates and returns a new child chromosome by performing the crossover operation on this chromosome and the other one that is passed in.
     * 
     * @param other chromosome of the other parent to cross with this chromosome
     * @return new child chromosome
     */
    public Chromosome crossover(Chromosome other){
        Chromosome child = new Chromosome();
        for (int i = 0; i < this.size(); i++){
            
            int rand = rng.nextInt(2);
            if (rand == 0){  // if random number is zero add item from this chomosome
                child.add(new Item(this.get(i)));
            }
            else{  // if random number is not zero add item from other chromosome 
                child.add(new Item(other.get(i)));
            }
            
        }
        return child;
    }

    /** 
     * Performs the mutation funciton on this chromosome.  
     * Each item in this chromosome has a 10% of flipping the true/false field.
    */
    public void mutate(){

        rng = new Random();
        for (int i = 0; i < super.size(); i++){  // loop through arraylist
            int rand = rng.nextInt(10);
            if (rand == 1){
                if (this.get(i).isIncluded() == true){  // change true to false
                    this.get(i).setIncluded(false);
                }
                else{  // change false to true
                    this.get(i).setIncluded(true);
                }
            }
        }
    }

    /**
     * Returns the fitness of this chromosome.  
     * If the sum of all of the included items' weights are greater than 10, the fitness is zero.  
     * Otherwise, the fitness is equal to the sum of all of the included items' values.
     * 
     * @return fitness level integer value
     */
    public int getFitness(){
        double weight = 0.0;
        int value = 0;
        for (int i = 0; i< this.size(); i++){
            if (this.get(i).isIncluded() == true){
                weight += this.get(i).getWeight();
                value += this.get(i).getValue();
            }
        }
        if (weight > 10){
            return 0;
        }
        else{
            return value;
        }
    }

    /**
     * Implements comparable interface to sort chromosomes by fitness value
     */
    @Override
    public int compareTo(Chromosome other){
        
        if (getFitness() > other.getFitness()){
            return -1;
        }
        else if (getFitness() < other.getFitness()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    /**
     * This method overrides toString(). 
     * Displays the name, weight, and value of all items in this chromosome whose included value is true followed by the fitness of this chromosome.
     */
    @Override
    public String toString(){
        String output = "";
        if (getFitness() > 0){   
            for (int i = 0; i < this.size(); i++){ 
                if (this.get(i).isIncluded() == true){
                output += this.get(i).toString() + ", "; // add the item, weight, and value to string
                }
            }
        output += "Fitness: " + getFitness();  // add fitness to string
        return output;
        }
        else{  
            return "Fitness is 0";
        }
    }
}
