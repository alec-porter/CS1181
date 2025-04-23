public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 10\n");

        boolean value;

        value = Matching.nestParen("(())");
        System.out.println("(()) is " + value);
        value = Matching.nestParen("((()))");
        System.out.println("((())) is " + value);
        value = Matching.nestParen("(((x))");
        System.out.println("(((x)) is " + value);
        value = Matching.nestParen("((())");
        System.out.println("((()) is " + value);
        value = Matching.nestParen("((()()");
        System.out.println("((()() is " + value);
        value = Matching.nestParen("");
        System.out.println("null is " + value);
        value = Matching.nestParen("(yy)");
        System.out.println("(yy) is " + value);
        value = Matching.nestParen("((yy())))");
        System.out.println("((yy()))) is " + value);
        value = Matching.nestParen("()()))");
        System.out.println("()())) is " + value);
        value = Matching.nestParen("()");
        System.out.println("() is " + value);
        value = Matching.nestParen("  ");
        System.out.println("  is " + value);



    }

    // ----- Lab 10 Part A Methods ----- //

    /**
     * Print Method
     * @param start start value (larger number)
     * @param stop end value (smaller number)
     */
    public static void countDown(int start, int stop){
        System.out.println("Counting down from " + start + " to " + stop + ":");
        helper(start, stop);
    }

    /**
     * Recursive Method - counts backwards from start to stop (inclusive)
     * @param start start value (larger number)
     * @param stop end value (smaller number)
     */
    public static void helper(int start, int stop){
        if (stop - 1 >= start){ //base case
            System.out.println();
        }
        else { // recursive case
            System.out.print(start + " ");
            helper((start-1), stop);
        }

    }

}
