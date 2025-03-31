/**
 * Event Class
 * 
 * @author Alec Porter
 */
public class Event implements Comparable<Event> {

    private int globalTime;
    private String eventName;
    private int truckID;
    public Object getGlobalString;
    
    /**
     * Constructor for truck event
     * 
     * @param globalTime time of event
     * @param truckID truck number
     * @param eventName event name
     */
    public Event(int globalTime, int truckID, String eventName){
        this.globalTime = globalTime;
        this.eventName = eventName;
        this.truckID = truckID;
    }

    /**
     * Constructor for train event
     * 
     * @param globalTime time of event
     * @param eventName event name
     */
    public Event(int globalTime, String eventName){
        this.globalTime = globalTime;
        this.eventName = eventName;
        this.truckID = -1;
    }

    /**
     * Event Time Getter
     * 
     * @return event time
     */
    public int getGlobalTime(){
        return globalTime;
    }

    /**
     * Event Name Getter
     * 
     * @return event name
     */
    public String getGlobalString(){
        return eventName;
    }

    /**
     * Truck ID Getter
     * 
     * @return truck id number
     */
    public int getTruckID(){
        return truckID;
    }

    /**
     * Event Time Setter
     * 
     * @param globalTime set new event time
     */
    public void setGlobalTime(int globalTime){
        this.globalTime = globalTime;
    }

    /**
     * Event Name Setter
     * 
     * @param eventName set new event name
     */
    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    /**
     * Sort events by time
     */
    @Override
    public int compareTo(Event other) {
        
        if (this.globalTime < other.globalTime){
            return -1;
        }
        else if (this.globalTime > other.globalTime){
            return 1;
        }
        else{
            return 0;
        }
        
    }

    /**
     * Custom print statements
     */
    @Override
    public String toString(){
        if (truckID < 0){
            return globalTime + ": " + eventName;
        }
        else{
            return globalTime + ": " + "TRUCK #" + truckID + " " + eventName;
        }
    }

    
}
