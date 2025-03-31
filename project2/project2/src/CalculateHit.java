/**
 * Class CalculateHit - calulates if a target is hit based on the target location and the mouse location in a jframe/jpanel
 * 
 * @author Alec Porter
 */
public class CalculateHit {
    
    // initialize variables
    int mouseXCoord;
    int mouseYCoord;
    int targetXCoord;
    int targetYCoord;
    int targetSize;
    int targetYOffset;
    boolean targetHit;
    int numOfHits = 0;

    /**
     * Constructor for the CalculateHit class
     * 
     * @param mouseXCoord mouse x-axis coordinate in pixels
     * @param mouseYCoord mouse y-axis coordinate in pixels
     * @param targetXCoord x-axis coordinate for a square drawn in a jpanel in pixels
     * @param targetYCoord y-axis coordinate for a square drawn in a jpanel in pixels
     * @param targetSize length for a square drawn in a jpanel in pixels
     * @param targetYOffset offset to account for top menubar height in pixels in order to set mouse y-coordinate srart in jframe area
     */
    public CalculateHit(int mouseXCoord, int mouseYCoord, int targetXCoord, int targetYCoord, int targetSize, int targetYOffset){
        this.mouseXCoord = mouseXCoord;
        this.mouseYCoord = mouseYCoord;
        this.targetXCoord = targetXCoord;
        this.targetYCoord = targetYCoord;
        this.targetSize = targetSize;
        this.targetYOffset = targetYOffset;
        this.targetHit = false;
    }

    /**
     * Setter for mouse x-coordinate in pixels
     * 
     * @param mouseXCoord mouse x-coordinate
     */
    public void setMouseXCoord(int mouseXCoord){
        this.mouseXCoord = mouseXCoord;
    }

    /**
     * Setter for square x-coordinate in pixels
     * 
     * @param targetXCoord square x-coordinate in pixels 
     */
    public void setTargetXCoord(int targetXCoord){
        this.targetXCoord = targetXCoord;
    }

    /**
     * Setter for mouse y-coordinate in pixels
     * 
     * @param mouseYCoord mouse y-coordinate
     */
    public void setMouseYCoord(int mouseYCoord){
        this.mouseYCoord = mouseYCoord;
    }

    /**
     * Setter for square y-coordinate in pixels
     * 
     * @param targetYCoord square y-corrdinate
     */
    public void setTargetYCoord(int targetYCoord){
        this.targetYCoord = targetYCoord;
    }

    /**
     * Setter for square length in pixels
     * 
     * @param targetSize square length
     */
    public void setTargetSize(int targetSize){
        this.targetSize = targetSize;
    }

    /**
     * Setter for square y-ccordinate offset from top of menu bar in pixels
     * 
     * @param targetYOffset square y-coordinate offset
     */
    public void setTargetYOffset(int targetYOffset){
        this.targetYOffset = targetYOffset;
    }

    /**
     * Getter for number of mouse clicks in square area
     * 
     * @return integer number of clicks in square area
     */
    public int getNumOfHits(){
        return numOfHits;
    }

    /**
     * Method to determine if mouse x and y coordinate inside square area
     * 
     * @return true if mouse click inside area or false if mouse not inside area
     */
    public boolean getValidHit(){
        if (mouseXCoord >= targetXCoord && mouseXCoord < targetXCoord + targetSize && mouseYCoord - targetYOffset >= targetYCoord && mouseYCoord - targetYOffset < targetYCoord + targetSize){
            numOfHits+=1;
            return true;
        }
        else{
            return false;
        }
    }


}
