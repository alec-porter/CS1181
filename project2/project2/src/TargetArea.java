import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * Class TargetArea - jpanel area for targt to move areound in 
 * 
 * code adapted from example provided in CS 1181: https://github.com/ClarissaMilligan/CS-1181-SP25/tree/main/Week-4-5-GUI/BouncingBallExample
 * 
 * @author Alec Porter
 */
public class TargetArea extends JPanel{

    // initialize variables and arrays
    private static Random rng;
    private int targetXPosition;
    private int targetYPosition;
    private int targetSize = 0;
    private int maxTargetSize = 50;
    private int targetSpeed = 20;
    private int timeLapse = 0;
    private int count;
    private int maxTime = 750;
    private boolean continueGame = true;
    private ArrayList<int[]> coords = new ArrayList<>();
    private int[] position = new int[2];
    private Pause pauseGame = new Pause();

    /**
     * default constructor
     */
    public TargetArea(){
        super();
    }

    /**
     * Method to paint a square located at provided x and y coordinate with provided length
     */
    public void paintComponent(Graphics square){
        super.paintComponent(square);
        square.fillRect(targetXPosition, targetYPosition, targetSize, targetSize);
    }

    /**
     * Method to add square to target area, move square until continue game flage set to false, and remove square from target area
     */
    public void targetMovement(){

        while(continueGame){

            if (timeLapse == 0){ // pause game so player can get ready
                pauseGame.setPauseTime(1000);
            }

            if (timeLapse < maxTargetSize){ // target enters area
                rng = new Random();
                targetXPosition = rng.nextInt(50, 450);  // select random coordinate to start 
                targetYPosition = rng.nextInt(50, 450);  // select random coordinate to start
                for (int i = 0; i < maxTargetSize; i++){  // square grows in size until it reaches the maximum size for the game
                    targetSize += 1;
                    this.invalidate();
                    this.repaint();
                    pauseGame.setPauseTime(20);
                    timeLapse += 1;
                }
            }

            // target moves around area
            NextLocation next = new NextLocation();  // create new object to calculate next location to move square to
            TargetVector vector = new TargetVector();  // create new vecotr of coordinates needed to move square
            next.calculateNextCoord(targetXPosition, targetYPosition);
            coords = vector.moveTarget(targetXPosition, targetYPosition, next.getXCoord(), next.getYCoord());  // get coordinate values for square movement and store in an arraylist
            count = coords.size();
            for (int i = 0; i < count; i++){ // loop through each coordinate pair in the arraylist
                position = coords.get(i);
                targetXPosition = position[0];  // x-coordinate to move square to
                targetYPosition = position[1];  // y-coordinate to more square to
                this.invalidate();
                this.repaint();
                pauseGame.setPauseTime(targetSpeed);
                timeLapse += 1;
            }

            if (timeLapse > maxTime){ // target leaves area
                for (int i = 0; i < maxTargetSize; i++){  // square shrinks in size to leave target area
                    targetSize -= 1;
                    this.invalidate();
                    this.repaint();
                    pauseGame.setPauseTime(20);
                }
                continueGame = false; // exit game
            }
            
        }
        this.invalidate();
        this.repaint();
        pauseGame.setPauseTime(1000);
    }

    /**
     * Getter for current square x position in play area
     * 
     * @return square x-coordinate in pixels
     */
    public int getTargetXPosition(){
        return targetXPosition;
    }

    /**
     * Getter for current square y position in play area
     * 
     * @return square y-coordinate in pixels
     */
    public int getTargetYPosition(){
        return targetYPosition;
    }

    /**
     * Getter for current size
     * 
     * @return square size in pixels
     */
    public int getTargetSize(){
        return targetSize;
    }

}
