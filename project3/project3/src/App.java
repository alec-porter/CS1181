import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Best Drone Percentate: 47
        System.out.println("Alec Porter Project 3 Best Drone Percentage 47\n");

        // initialize variables
        ArrayList<Truck> trucks = new ArrayList<>();
        PriorityQueue<Event> eventQueue = new PriorityQueue<>();
        Queue<Event> truckQueue = new LinkedList<>();
        Event currentEvent;
        Event currentTruck;

        // caluclate number of drones and trucks based on drone percentage
        double PERCENT_BY_DRONE = 25;
        int totalPackages = 1500;
        int droneSorties = (int) Math.ceil(totalPackages * PERCENT_BY_DRONE/100.0); // number of drones used rounded up to nearest int
        int truckSorties = (int) Math.ceil((totalPackages - droneSorties)/10.0);  // number of truckes used rounded up to nearest int
        // print number of drones and trucks to screen
        System.out.println("With " + PERCENT_BY_DRONE + "% drones and 1500 packages,");
        System.out.println("There will be:");
        System.out.println("- " + droneSorties + " drones");
        System.out.println("- " + truckSorties + " trucks\n");
        
        // load in train schedule from file and print to screen
        ArrayList<int[]> trainSchedule = new ArrayList<>();
        trainSchedule = readTrainSchedule("train_schedule.txt");
        System.out.println("TRAIN SCHEDULE");
        System.out.println("--------------");
        for (int i = 0; i < trainSchedule.size(); i++){
            System.out.println(trainSchedule.get(i)[0] + " - " + trainSchedule.get(i)[1]);
        }
        System.out.println("");        

        // create truck objects and store in arraylist
        for (int i = 0; i < truckSorties; i++){
            trucks.add(new Truck(i));
        }

        // add truck start events to event queue
        for (int i = 0; i < truckSorties; i++){
            eventQueue.add(new Event(i * 15, i, "begins journey"));
        }

        // add truck arrive at crossing events to event queue
        for (int i = 0; i < truckSorties; i++){
            eventQueue.add(new Event(i * 15 + 3000 / 30, i, "waits at crossing"));
        }

        // add train events to event queue
        for (int i = 0; i < trainSchedule.size(); i++){
            eventQueue.add(new Event(trainSchedule.get(i)[0], "TRAIN arrives at crossing"));
            eventQueue.add(new Event(trainSchedule.get(i)[1], "TRAIN leaves crossing"));
        }


        while(!eventQueue.isEmpty()){  // run simulation until event queue is empty

            currentEvent = eventQueue.peek();
            currentTruck = truckQueue.peek();
            
            // if there are trucks in the queue, process event/truck queues
            while (!truckQueue.isEmpty()){
                currentTruck = truckQueue.peek();
                currentEvent = eventQueue.peek();
                int eventTime = currentEvent.getGlobalTime();
                int eventID = currentEvent.getTruckID();
                String eventType = currentEvent.getGlobalString();
                int truckTime = currentTruck.getGlobalTime();                
                int truckID = currentTruck.getTruckID();
                String truckType = currentTruck.getGlobalString();
                int queueLength = truckQueue.size();
                
                if (eventType.contains("waits") && eventTime > truckTime){ // remove trucks from queue until new truck arrives
                    int time = truckTime + (27000 / 30);
                    eventQueue.add(new Event(time, truckID, "completes journey")); // create complete journey event for dequeued truck
                    System.out.println(truckQueue.poll());
                }
                else if (eventType.contains("waits") && eventTime <= truckTime){ // truck arrives at crosisng behind queue
                    int time = eventTime + queueLength;
                    truckQueue.add(new Event(time, eventID, "crosses crossing")); // add truck to truck queue
                    trucks.get(eventID).updateTruckCross(time);
                    System.out.println(eventQueue.poll()); 
                    
                }
                else if ((eventType.contains("begins") || eventType.contains("complete"))){  // poll events that don't need to be added to truck queue
                    System.out.println(eventQueue.poll());
                    if (eventQueue.peek() == null){ // if there are no more events then poll trucks from queue
                        int time = truckTime + (27000 / 30);
                        eventQueue.add(new Event(time, truckID, "completes journey")); // add truck complete journey event
                        System.out.println(truckQueue.poll());
                    }
                    
                }
                else if (eventType.contains("TRAIN arrives") && eventTime > truckTime){ // truck can cross prior to train arrival
                    System.out.println(truckQueue.poll());
                }
                else if (eventType.contains("TRAIN arrives") && eventTime <= truckTime){  // train arrives so truck queues
                    
                    System.out.println(eventQueue.poll());
                    currentEvent = eventQueue.peek();
                    // if next event is truck releated
                    while(!currentEvent.getGlobalString().contains("TRAIN leaves")){
                        
                        // poll truck that begins journey
                        if (currentEvent.getGlobalString().contains("begins")){
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                        // poll truck that ends journey
                        else if (currentEvent.getGlobalString().contains("completes")){
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                        // add arrivals to truck queue
                        else if (currentEvent.getGlobalString().contains("waits")){
                            truckQueue.add(currentEvent);
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                    }
                }
                else if (eventType.contains("TRAIN leaves")){
                    // train leaves crossing, update truck queue times
                    int startTime = currentEvent.getGlobalTime();
                    int wait = 1;
                    for(Event n : truckQueue){  // iterate through truck queue and update time
                        n.setGlobalTime(startTime + wait);
                        n.setEventName("crosses crossing");
                        int ID = n.getTruckID();
                        int time = n.getGlobalTime();
                        trucks.get(ID).updateTruckCross(time);
                        wait++;
                    }
                    System.out.println(eventQueue.poll());

                }
            }

            // if there are not trucks in the queue, process events
            if (truckQueue.isEmpty()){
                // poll truck begins journey
                if (currentEvent.getGlobalString().contains("begins")){
                    System.out.println(eventQueue.poll());
                }
                // poll truck completes journey
                else if (currentEvent.getGlobalString().contains("completes")){
                    System.out.println(eventQueue.poll());
                }
                // poll tuck waiting at crossing
                else if (currentEvent.getGlobalString().contains("waits")){
                    // if no truck queue, update event name, add complete event, update truck object
                    currentEvent.setEventName("crosses crossing");
                    int truckID = currentEvent.getTruckID();
                    int time = currentEvent.getGlobalTime();
                    trucks.get(truckID).updateTruckCross(time);
                    time = time + (27000 / 30);
                    eventQueue.add(new Event(time, truckID, "completes journey"));
                    System.out.println(eventQueue.poll());
                }
                // manage truck events between train arrival and leaving
                else if (currentEvent.getGlobalString().contains("TRAIN arrives")){
                    System.out.println(eventQueue.poll());
                    // look at next event and process action
                    currentEvent = eventQueue.peek();
                    // if next event is truck related, process action
                    while(!currentEvent.getGlobalString().contains("TRAIN leaves")){
                        // poll truck begins journey
                        if (currentEvent.getGlobalString().contains("begins")){
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                        // poll truck ends journey
                        else if (currentEvent.getGlobalString().contains("completes")){
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                        // add arrivals to truck queue
                        else if (currentEvent.getGlobalString().contains("waits")){
                            truckQueue.add(currentEvent);
                            System.out.println(eventQueue.poll());
                            currentEvent = eventQueue.peek();
                        }
                    }
                    // train leaves crossing, update truck queue
                    currentEvent = eventQueue.poll();
                    int startTime = currentEvent.getGlobalTime();
                    int wait = 1;
                    for(Event n : truckQueue){  // iterate through truck queue and update time
                        n.setGlobalTime(startTime + wait);
                        n.setEventName("crosses crossing");
                        int truckID = n.getTruckID();
                        int time = n.getGlobalTime();
                        trucks.get(truckID).updateTruckCross(time);
                        wait++;
                    }
                    System.out.println(currentEvent);
                }
            }
        }


        // Stats
        System.out.println("\n\nSTATS");
        System.out.println("-----\n");
        int totalTime = 0;
        int truckLastTime = 0;
        int finalTime = 0;
        int droneLastTime = (droneSorties - 1) * 3 + 30000 / 500;
        // display stats for all trucks
        for (int i = 0; i < trucks.size(); i++){ 
            System.out.println(trucks.get(i));
            totalTime += trucks.get(i).getTruckTotalTime();
            truckLastTime = trucks.get(i).getTruckEnd();
        }
        int avgTime = (int) Math.ceil((double)(totalTime) / trucks.size());
        System.out.println("\nTRUCK AVG TRIP TIME: " + avgTime + " minutes");
        System.out.println("TRUCK TOTAL TIME: " + truckLastTime + " minutes");
        // display drone stats
        System.out.println("\nDRONE TRIP TIME: " + 30000 / 500 + " minutes");
        System.out.println("DRONE TOTAL TIME: " + droneLastTime + " minutes\n");

        if (droneLastTime >= truckLastTime){
            System.out.println("TOTAL TIME: " + droneLastTime);
            finalTime = droneLastTime;
        }
        else{
            System.out.println("TOTAL TIME: " + truckLastTime);
            finalTime = truckLastTime;
        }


    }
    /**
     * Method to read in a train schedule from a file
     * 
     * @param filename input filename
     * @return returns an arraylist of train crossing arrive/leave times 
     * @throws FileNotFoundException notifies user file not found
     */
    public static ArrayList<int[]> readTrainSchedule(String filename) throws FileNotFoundException{

        // initialize return array
        ArrayList<int[]> trainSchedule = new ArrayList<>();

        try{
            File fileHandle = new File(filename);  // open file
            Scanner inputFile = new Scanner(fileHandle);
            while (inputFile.hasNextLine()){
                String textLine = inputFile.nextLine();  // read in line from file
                // split data, parse to int, store arrive time, calculate and store leave time
                String[] textLineSplit = textLine.split(" ", 2);  
                int trainStart = Integer.parseInt(textLineSplit[0]);
                int trainBlock = Integer.parseInt(textLineSplit[1]);
                int [] times = new int[2];
                times[0] = trainStart;
                times[1] = trainStart + trainBlock;
                trainSchedule.add(times);
            }
            inputFile.close();
        }
        catch (FileNotFoundException e){ // exit program if file not found
            System.out.println("Not a valid file name.");
            System.exit(0);
        }
        return trainSchedule;
    }

}
