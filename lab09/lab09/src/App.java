import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 9\n\n");

        // initialize
        Stack<Integer> intStack = new Stack<>();
        Queue<Integer> intQueue = new ArrayDeque<>();
        TileGame game = new TileGame();
        int numTurns;

        // add values to stack
        intStack.push(3);
        intStack.push(2);
        intStack.push(1);
        intStack.push(2);

        // add values to queue
        intQueue.offer(1);
        intQueue.offer(2);
        intQueue.offer(2);
        intQueue.offer(1);
        intQueue.offer(3);
        intQueue.offer(1);
        intQueue.offer(2);
        intQueue.offer(1);
        intQueue.offer(2);
        intQueue.offer(3);

        // run game and output turns
        numTurns = game.tileGame(intStack, intQueue);
        System.out.println("Give stack and queue took " + numTurns + " turns to finish playing");


    }

}
