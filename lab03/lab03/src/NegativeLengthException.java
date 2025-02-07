/**
 * Custom exception for negative number input
 * 
 * @author Alec Porter
 */
public class NegativeLengthException extends Exception{
    public NegativeLengthException(String output){
        super(output);
    }
}
