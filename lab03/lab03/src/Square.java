/**
 * Class Square - calculates the perimeter and area of a square based on the length of the side provided to the class.
 * 
 * @author Alec Porter
 */
public class Square {
    
    // initialize variables
    private double side;

    /**
     * Create new square.  Will throw error if negative number is input.
     * 
     * @param side length of the side of the square
     */
    public Square(double side) throws NegativeLengthException{
        if (side >= 0){
            this.side = side;
        }
        else{
            throw new NegativeLengthException("Error: Negative Length: " + side);
        }
    }

    /**
     * Returns the perimeter of the square based on the length of the side.
     * 
     * @return perimeter of the square
     */
    public double getPerimeter(){
        return side*4.0;
    }

    /**
     * Returns the area of the square based on the length of the side.
     * 
     * @return area of the square
     */
    public double getArea(){
        return side*side;
    }

    /**
     * Overrides toString to print the length of the square's side
     * 
     * @return string
     */
    @Override
    public String toString(){
        return "Square with side = " + side;
    }

}
