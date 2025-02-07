/**
 * SecureUser Class - a sub class of the User Class
 * 
 * SecureUser inherets username and password and updates authentication to account for the number of autentication failures.  
 * More than three authentications failures "locks" the user account.
 * 
 * @auther Alec Porter
 */
public class SecureUser extends User {

    private int numFailedAttempts = 0;  // initialize number of failed attempts

    //SecureUser constructor
    public SecureUser(String username, String password, int inputNumFailedAttempts){
        super (username, password);
        numFailedAttempts = inputNumFailedAttempts;
    }

    // getter for number of failed attempts
    private int getNumFailedAttemps (){
        return numFailedAttempts;
    }

    // setter for number of failed attempts
    private void setNumFailedAttempts(int inputNumFailedAttempts){
        numFailedAttempts = inputNumFailedAttempts;
    }

    /**
     * This method overrides the authenticate method in parent.
     * It verifies if the unser input a valid password in less than three attempts.
     *  
     * @param inputPassword user input to check against password 
     * @return boolean value indicating if the user input a valid password in less than 3 attempts
     */
    @Override 
    public boolean authenticate(String inputPassword){
        if (getNumFailedAttemps()>=3){  // if number of failed attempts is 3 or more, always return false
            return false;
        }
        else{
            if (super.authenticate(inputPassword)==false){  // increment number of false inputs
                setNumFailedAttempts(getNumFailedAttemps()+1);
                return false;
            }
            else{ // successful login, reset failed attempts counter
                setNumFailedAttempts(0);
                return super.authenticate(inputPassword);
            }
        }
        
    }
}
