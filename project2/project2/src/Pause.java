/**
 * Class Pause - pauses the game for a certian amount of time based on input
 * 
 * @author Alec Porter
 */
public class Pause {

    // initialize variable
    int pauseTime;

    // default constructor
    public Pause(){
        pauseTime = 0;
    }

    /**
     * Setter for puase time
     * 
     * @param pauseTime time in milliseconds
     */
    public void setPauseTime(int pauseTime){
        this.pauseTime = pauseTime;
        try{
        Thread.sleep(pauseTime);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
