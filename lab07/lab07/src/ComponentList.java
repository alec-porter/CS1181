import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Class ComponentList inherits from JPanel and is bounded to JComponent
 * 
 * @author Alec Porter
 */
public class ComponentList <T extends JComponent> extends JPanel{

    private ArrayList<T> contents = new ArrayList<>();
    
    /**
     * No argument constructor
     */
    public ComponentList(){
    }

    /**
     * ComponentList takes in an ArrayList and the constructor adds all of the items in the ArrayList to the current ComponentList
     * 
     * @param input ArrayList of abstract data type T
     */
    public ComponentList(ArrayList<T> input){
        // loop through each T in the input and add to ComponentList
        for(T x : input){
            super.add(x);
        }
        // add all items in the input to the arraylist
        contents.addAll(input);
    }

    /**
     * add a new item to the current ComponentList
     * 
     * @param input item of abstract data type T
     */
    public void add(T input){
        // add new input to ComponentList and arraylist
        super.add(input);
        contents.add(input);
    }

    /**
     * setComponentAtIndex takes in an integer for the index and a component and sets the component at the index to the new item
     * 
     * @param index index to replace item at
     * @param input new item to replace old item
     */
    public void setComponentAtIndex(int index, T input){
        // remove item at index and replace it with input
        super.remove(index);
        super.add(input, index);
        contents.set(index, input);
    }

}
