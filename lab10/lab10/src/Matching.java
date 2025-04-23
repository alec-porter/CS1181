/**
 * Matching - implements recursive method to check pairs of parenthesis in a string
 * 
 * @author Alec Porter
 */
public class Matching {

    /**
     * No argument constructor
     */
    public Matching(){
    }
    
    /**
     * Recursive method - return true if n is a nesting of zero or more pairs of parenthesis, like “(())” or “((()))” and false otherwise.
     * 
     * @param n string input
     * @return true or false if string follows pairs of parenthesis pattern
     */
    public static boolean nestParen(String n){

        boolean nest;

        // strings of odd length will always return false
        if (n.length() % 2 != 0){ 
            return false;
        }
        // Base case
        if (n.length() == 0){
            return true;
        }

        else{
            int divider = n.length()/2;
            String value = n.substring(divider-1, divider+1); // string of middle two indices
            String remaining = n.substring(0, divider-1) + n.substring(divider+1); // remaining prior and aft indices
            if (value.equals("()")){ // continue recursion if string value is "()"
                return nestParen(remaining);
            }
            else{ // stop recursion if string value is not "()"
                nest = false;
            }
            return nest;
        }
    }
}
