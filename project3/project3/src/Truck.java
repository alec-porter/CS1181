/**
 * Truck Class - stores data on delivery trucks
 * 
 * @author Alec Porter
 */
public class Truck {

    private int truckNumber;
    private int TRUCK_START;
    private int TRUCK_AT_CROSSING;
    private int TRUCK_CROSS;
    private int TRUCK_END;
    private int TOTAL_TIME;


    /**
     * Truck Constructor - create truck stats based on truck number
     * 
     * @param truckNumber truck number
     */
    public Truck(int truckNumber){
        this.truckNumber = truckNumber;
        TRUCK_START = truckNumber * 15; // set start time
        TRUCK_AT_CROSSING = truckNumber * 15 + 3000 / 30; // set cross time
    }

    /**
     * Time Setter - updates times based on input
     * 
     * @param input updated time truck crossed
     */
    public void updateTruckCross(int input){ // update times based on new cross time
        TRUCK_CROSS = input;
        TRUCK_END = TRUCK_CROSS + 27000 / 30;
        TOTAL_TIME = TRUCK_END - TRUCK_START;
    }

    /**
     * Truck Number Getter
     * 
     * @return returns truck number
     */
    public int getTruckNumber(){
        return truckNumber;
    }

    /**
     * Truck Start Time Getter
     * 
     * @return start time
     */
    public int getTruckStart(){
        return TRUCK_START;
    }

    /**
     * Truck at Crossing Time Getter
     * 
     * @return time arrived at crossing
     */
    public int getTruckAtCrossing(){
        return TRUCK_AT_CROSSING;
    }

    /**
     * Truck End Time Getter
     * 
     * @return end time
     */
    public int getTruckEnd(){
        return TRUCK_END;
    }

    /**
     * Truck Total Time Getter
     * 
     * @return total time
     */
    public int getTruckTotalTime(){
        return TOTAL_TIME;
    }

    /**
     * Custom print message
     */
    @Override
    public String toString(){
        return "TRUCK #" + truckNumber + " total trip time: " + TOTAL_TIME + " minutes";
    }
    
    
}
