/**
 * Class Golfer - contains a golfer's game information
 * 
 * A golfer object contains the golfer's first and last name, their score, and the number of holes completed.
 * 
 * @author Alec Porter
 */
public class Golfer implements Comparable<Golfer>{
    
    private String firstName;
    private String lastName;
    private int score;
    private int holesCompleted;

    // Golfer constructor
    public Golfer(String firstName, String lastName, int score, int holesCompleted){
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.holesCompleted = holesCompleted;
    }

    /**
     * This method overrides the string method and prints the current game status of the golfer.
     * 
     * @return string of the form: lastName, firstName: score with holesCompleted holes completed
     */
    @Override
    public String toString(){
        return lastName + ", " + firstName + ": " + score + " with " + holesCompleted + " holes completed";
    }

    /**
     * Implements the Comparable interface to sort golfers by score, then holes completed, then last name, then first name
     */
    public int compareTo(Golfer otherGolfer){

        if (this.score < otherGolfer.score){ // sort by score
            return -1;
        }
        else if (this.score > otherGolfer.score){ // sort by score
            return 1;
        }
        else {
            
            if (this.holesCompleted < otherGolfer.holesCompleted){ // sort by holes completed 
                return 1;
            }

            else if (this.holesCompleted > otherGolfer.holesCompleted){ // sort by holes completed 
                return -1;
            }

            else{
                
                if (this.lastName.compareToIgnoreCase(otherGolfer.lastName) < 0){  // sort by last name
                    return -1;
                }

                else if (this.lastName.compareToIgnoreCase(otherGolfer.lastName) > 0){  // sort by last name
                    return 1;
                }
                
                else {
                    
                    if (this.firstName.compareToIgnoreCase(otherGolfer.firstName) < 0){  // sort by first name
                        return -1;
                    }

                    else if (this.firstName.compareToIgnoreCase(otherGolfer.firstName) > 0) { // sort by first name
                        return 1;
                    }

                    else {
                        return 0;
                    }

                }

            }

        }
    }


        

    

}
