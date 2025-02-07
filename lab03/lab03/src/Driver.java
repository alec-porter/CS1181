import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CS1181 Lab 3 - Exceptional Square
 * 
 * @author Alec Porter
 */
public class Driver{
    public static void main(String[] args) throws Exception {

        Scanner userInput = new Scanner(System.in);  

        boolean invalidInput = true;
        
        while(invalidInput){

            try {

                System.out.print("Enter the length of the square's sides: ");  // request input from user
                double inputValue = userInput.nextDouble();  // store user input
                
                Square square1 = new Square(inputValue);  // creat new square
                
                double area = square1.getArea();  // get area
                double perimeter = square1.getPerimeter();  // get perimeter

                // print square information
                System.out.println(square1);
                System.out.printf("The perimieter of the square is %.2f\n", perimeter);
                System.out.printf("The area of the square is %.2f\n", area);

                invalidInput = false;
                
            } 
            catch (NegativeLengthException nle) {  // error message for negative number
                System.out.println(nle.getMessage());
            }
            catch (InputMismatchException ime){  // error message for non-number input
                System.out.println("Error: You must enter a number");
                userInput.nextLine();
            }
        }

        userInput.close();

    }
}
