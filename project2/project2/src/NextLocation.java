import java.util.Random;

/**
 * Class NextLocation - calculates a random x and y coordinate based in a provided x and y coordinate so the target can randomly move around the play area
 * 
 * @author Alec Porter
 */
public class NextLocation {

    // initalize variable
    private static Random rng;
    private int quad;
    private int nextTargetXCoord;
    private int nextTargetYCoord;

    // default constructor
    public NextLocation(){
        nextTargetXCoord = 0;
        nextTargetYCoord = 0;
    }

    /**
     * Method to calculate next location for target to move to based on current location
     * 
     * @param currTargetXCoord current x-coordinate of target
     * @param currTargetYCoord current y-corrdinate of target
     */
    public void calculateNextCoord(int currTargetXCoord, int currTargetYCoord){

        // if current coordinate is in upper left (quad 1) then move to one of the other quads
        if (currTargetXCoord >= 0 && currTargetXCoord <= 250 && currTargetYCoord >= 0 && currTargetYCoord <= 250){
            rng = new Random();
            quad = rng.nextInt(3);
            if (quad == 0){  // random number from quad 2
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(10, 250);
            }
            else if (quad == 1){  // random number from quad 3
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(250, 450);
            }
            else if (quad == 2){  // random number from quad 4
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(250, 450);
            }
        }

        // if current coordinate is in upper right (quad 2) then move to one of the other quads
        if (currTargetXCoord > 250 && currTargetXCoord <= 500 && currTargetYCoord >= 0 && currTargetYCoord <= 250){
            rng = new Random();
            quad = rng.nextInt(3);
            if (quad == 0){  // random number from quad 1
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(10, 250);
            } 
            else if (quad == 1){  // random number from quad 3
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(250, 450);
            }
            else if (quad == 2){  // random number from quad 4
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(250, 450);
            }           
        }
        
        // if current coordinate is in lower left (quad 3) then move to one of the other quads
        if (currTargetXCoord >= 0 && currTargetXCoord <= 250 && currTargetYCoord > 250 && currTargetYCoord <= 500){
            rng = new Random();
            quad = rng.nextInt(3);
            if (quad == 0){  // random number from quad 1
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(10, 250);
            } 
            else if (quad == 1){  // random number from quad 2
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(10, 250);
            }
            else if (quad == 2){  // random number from quad 4
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(250, 450);
            }  
        }

        // if current coordinate is in lower right (quad 4) then move to one of the other quads
        if (currTargetXCoord > 250 && currTargetXCoord <= 500 && currTargetYCoord > 250 && currTargetYCoord <= 500){
            rng = new Random();
            quad = rng.nextInt(3);
            if (quad == 0){  // random number from quad 1
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(10, 250);
            } 
            else if (quad == 1){  // random number from quad 2
                nextTargetXCoord = rng.nextInt(250, 450);
                nextTargetYCoord = rng.nextInt(10, 250);
            }
            else if (quad == 2){  // random number from quad 3
                nextTargetXCoord = rng.nextInt(10, 250);
                nextTargetYCoord = rng.nextInt(250, 450);
            }  
        }
    }

    /**
     * Getter for the target's next x-coordinate used for movement
     * 
     * @return next x-coordiante
     */
    public int getXCoord(){
        return this.nextTargetXCoord;
    }

    /**
     * Getter for the target's next y-coordinate used for movement
     * 
     * @return next y-coordinate
     */
    public int getYCoord(){
        return this.nextTargetYCoord;
    }

}
