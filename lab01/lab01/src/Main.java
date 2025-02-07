public class Main {
    public static void main(String[] args) {

        // User user1 = new User("Michelle", "12345");
        // System.out.println("1. " + user1.isValidPassword()); // false -- less than 8 characters

        // User user2 = new User("Michelle", "12345Michelle");
        // System.out.println("2. " + user2.isValidPassword()); // false -- contains username

        // User user3 = new User("Michelle", "12345678");
        // System.out.println("3. " + user3.isValidPassword()); // true

        // System.out.println("4. " + user2.authenticate("ABCDE")); // false -- incorrect password
        // System.out.println("5. " + user2.authenticate("12345Michelle")); // true 

        // System.out.println("6. " + user3.authenticate("12345678")); // true

        // Test Case 1
        System.out.println("\nCase 1 - All F (3 consecutive fails, locked, fail even with correct password tried twice)");
        SecureUser secUser1 = new SecureUser("Michelle", "hello123", 0);
        System.out.println(secUser1.authenticate("hello"));
        System.out.println(secUser1.authenticate("hello12"));
        System.out.println(secUser1.authenticate("Hello123"));
        System.out.println(secUser1.authenticate("hello123"));
        System.out.println(secUser1.authenticate("hello123"));

        // Test Case 2
        System.out.println("\nCase 2 - F (failed), F (failed), T (success and reset counter), F (failed), T (success and reset counter)");
        SecureUser secUser2 = new SecureUser("Michelle", "hello123", 0);
        System.out.println(secUser2.authenticate("hello"));
        System.out.println(secUser2.authenticate("hello12"));
        System.out.println(secUser2.authenticate("hello123"));
        System.out.println(secUser2.authenticate("hello"));
        System.out.println(secUser2.authenticate("hello123"));

        // Test Case 3
        System.out.println("\nCase 3 - T (success and reset counter), F (failed), F (failed), F (failed), F (correct password but locked)");
        SecureUser secUser3 = new SecureUser("Michelle", "hello123", 0);
        System.out.println(secUser3.authenticate("hello123"));
        System.out.println(secUser3.authenticate("hello"));
        System.out.println(secUser3.authenticate("heLLo123"));
        System.out.println(secUser3.authenticate("hello"));
        System.out.println(secUser3.authenticate("hello123"));


    }
}
