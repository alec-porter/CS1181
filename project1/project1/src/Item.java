/**
 * Item Class
 * 
 * @author Alec Porter
 */
public class Item {

    // initialize item values
    private final String name;
    private final double weight;
    private final int value;
    private boolean included;
    
    /**
     * Item constuctor
     * 
     * @param name name of item
     * @param weight weight of item in pounds
     * @param value of item rounded to nearest dollar
     */
    public Item(String name, double weight, int value){
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.included = false;
    }

    /**
     * Item constuctor
     * 
     * @param other item object
     */
    public Item(Item other){
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        this.included = other.included;
    }

    /**
     * Getter to return item weight
     * 
     * @return item's weight
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Getter to return item value
     * 
     * @return item's value
     */
    public int getValue(){
        return value;
    }

    /**
     * Getter to return if item is included
     * 
     * @return item's included status
     */
    public boolean isIncluded(){
        return included;
    }

    /**
     * Setter to change if item is included
     * 
     * @param included change item's include value
     */
    public void setIncluded(boolean included){
        this.included = included;
    }

    /**
     * This mehod overrides toString to print out item details.
     * 
     * @return string of item details (name, weight, and value).
     */
    @Override
    public String toString(){
        return name + " (" + weight + " lbs, $" + value + ")";   
    }

}
