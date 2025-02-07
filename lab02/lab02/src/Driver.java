import java.util.ArrayList;
import java.util.Collections;

public class Driver {
    public static void main(String[] args) throws Exception {
        
        // create arraylist to store golfer objects
        ArrayList<Golfer> golfers = new ArrayList<>();

        // test cases
        // golfers.add(new Golfer("C", "SMITH", -10, 10));
        // golfers.add(new Golfer("B", "smith", -10, 10));
        // golfers.add(new Golfer("A", "Doe", -10, 10));
        // golfers.add(new Golfer("F", "Doe", -10, 10));
        // golfers.add(new Golfer("D", "Smith", -10, 11));
        // golfers.add(new Golfer("A", "Smith", -10, 12));
        // golfers.add(new Golfer("A", "Smith", -13, 10));
        // golfers.add(new Golfer("A", "Smith", -14, 10));

        // add golfers
        golfers.add(new Golfer("Michael", "Brooks", -12, 14));
        golfers.add(new Golfer("Amy", "Daniels", -12, 14));
        golfers.add(new Golfer("Sarah", "Adams", -10, 15));
        golfers.add(new Golfer("Abby", "Adams", -8, 16));
        golfers.add(new Golfer("Peter", "Collins", -8, 16));
        
        // print golfers arraylist
        System.out.println(golfers);

        // print golfer status on indiviuals lines
        for (Golfer person : golfers){
            System.out.println(person);
        }

        System.out.println();
        
        // sort and print golfers arraylist
        Collections.sort(golfers);
        System.out.println(golfers);

        // print golfer status on indiviuals lines
        for (Golfer person : golfers){
            System.out.println(person);
        }

    }
}
