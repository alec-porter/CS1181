import java.util.ArrayList;

/**
 * Class Target Vector - calculates the series of x and y coordinates to move the square through in the target area from one point to another point
 * 
 * @author Alec Porter
 */
public class TargetVector {

    // arraylist to store coordinates
    private ArrayList<int[]> targetVector = new ArrayList<>();

    /**
     * default constructor
     */
    public TargetVector(){
    }

    /**
     * Method to create path vector of x and y coordinates, the path vector is a array of coordinate pairs between two points
     * 
     * coordinate pairs are calculated based on the slope between the two points and scaled using the shortest delta axis value
     * 
     * @param currTargetXCoord current x-coordinate of square
     * @param currTargetYCoord current y-coordinate of square
     * @param nextTargetXCoord x-coordinate to move square to
     * @param nextTargetYCoord y-coordinate to move square to
     * @return arraylist of x and y coordinate pairs
     */
    public ArrayList<int[]> moveTarget(int currTargetXCoord, int currTargetYCoord, int nextTargetXCoord, int nextTargetYCoord){

        // changes coordinate to avoid divide by zero errors
        if (nextTargetYCoord == currTargetYCoord){
            nextTargetYCoord += 1;
        }
        if (nextTargetXCoord == currTargetXCoord){
            nextTargetXCoord += 1;
        }

        // if delta y is less than delta x then create path corrdinates
        if ( Math.abs(nextTargetYCoord - currTargetYCoord) < Math.abs(nextTargetXCoord - currTargetXCoord)){
            if (nextTargetXCoord > currTargetXCoord && nextTargetYCoord > currTargetYCoord){  // path coords to point right and below current point
                for (int i = 1; i <= Math.abs(nextTargetYCoord - currTargetYCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = (int)(currTargetXCoord + Math.abs((double)(nextTargetXCoord - currTargetXCoord) / (double)(nextTargetYCoord - currTargetYCoord) * i));
                    copy[1] = currTargetYCoord + i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetXCoord > currTargetXCoord && nextTargetYCoord < currTargetYCoord){  // path coords to point right and above current point
                for (int i = 1; i <= Math.abs(nextTargetYCoord - currTargetYCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = (int)(currTargetXCoord + Math.abs((double)(nextTargetXCoord - currTargetXCoord) / (double)(nextTargetYCoord - currTargetYCoord) * i));
                    copy[1] = currTargetYCoord - i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetXCoord < currTargetXCoord && nextTargetYCoord > currTargetYCoord){  // path coords to point left and below current point
                for (int i = 1; i <= Math.abs(nextTargetYCoord - currTargetYCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = (int)(currTargetXCoord - Math.abs((double)(nextTargetXCoord - currTargetXCoord) / (double)(nextTargetYCoord - currTargetYCoord) * i));
                    copy[1] = currTargetYCoord + i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetXCoord < currTargetXCoord && nextTargetYCoord < currTargetYCoord){  // path coords to point left and above current point
                for (int i = 1; i <= Math.abs(nextTargetYCoord - currTargetYCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = (int)(currTargetXCoord - Math.abs((double)(nextTargetXCoord - currTargetXCoord) / (double)(nextTargetYCoord - currTargetYCoord) * i));
                    copy[1] = currTargetYCoord - i;
                    targetVector.add(copy);
                }
            }
        }

        // if delta x is less than delta y then create path corrdinates
        else if ( Math.abs(nextTargetXCoord - currTargetXCoord) < Math.abs(nextTargetYCoord - currTargetYCoord)){
            if (nextTargetYCoord > currTargetYCoord && nextTargetXCoord > currTargetXCoord){  // path coords to point right and below current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[1] = (int)(currTargetYCoord + Math.abs((double)(nextTargetYCoord - currTargetYCoord) / (double)(nextTargetXCoord - currTargetXCoord) * i));
                    copy[0] = currTargetXCoord + i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetYCoord > currTargetYCoord && nextTargetXCoord < currTargetXCoord){  // path coords to point left and below current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[1] = (int)(currTargetYCoord + Math.abs((double)(nextTargetYCoord - currTargetYCoord) / (double)(nextTargetXCoord - currTargetXCoord) * i));
                    copy[0] = currTargetXCoord - i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetYCoord < currTargetYCoord && nextTargetXCoord > currTargetXCoord){  // path coords to point right and above current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[1] = (int)(currTargetYCoord - Math.abs((double)(nextTargetYCoord - currTargetYCoord) / (double)(nextTargetXCoord - currTargetXCoord) * i));
                    copy[0] = currTargetXCoord + i;
                    targetVector.add(copy);
                }
            }
            else if (nextTargetYCoord < currTargetYCoord && nextTargetXCoord < currTargetXCoord){  // path coords to point left and above current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[1] = (int)(currTargetYCoord - Math.abs((double)(nextTargetYCoord - currTargetYCoord) / (double)(nextTargetXCoord - currTargetXCoord) * i));
                    copy[0] = currTargetXCoord - i;
                    targetVector.add(copy);
                }
            }
        }

        // if delta x equals delta y then create path coordinates
        else{
            if (currTargetXCoord > nextTargetXCoord && currTargetYCoord > nextTargetYCoord){  // path coords to point left and above current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = currTargetXCoord - i;
                    copy[1] = currTargetYCoord - i;
                    targetVector.add(copy);
                }
            }
            if (currTargetXCoord > nextTargetXCoord && currTargetYCoord < nextTargetYCoord){  // path coords to point left and below current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = currTargetXCoord - i;
                    copy[1] = currTargetYCoord + i;
                    targetVector.add(copy);
                }
            }

            else if (currTargetXCoord < nextTargetXCoord && currTargetYCoord > nextTargetYCoord){  // path coords to point right and above current point
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){
                    int[] copy = new int[2];
                    copy[0] = currTargetXCoord + i;
                    copy[1] = currTargetYCoord - i;
                    targetVector.add(copy);
                }
            }

            else if (currTargetXCoord < nextTargetXCoord && currTargetYCoord < nextTargetYCoord){
                for (int i = 1; i <= Math.abs(nextTargetXCoord - currTargetXCoord); i++){  // path coords to point right and below current point
                    int[] copy = new int[2];
                    copy[0] = currTargetXCoord + i;
                    copy[1] = currTargetYCoord + i;
                    targetVector.add(copy);
                }
            }
        }

        return targetVector;
    }
} 