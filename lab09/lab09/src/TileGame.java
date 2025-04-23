import java.util.Queue;
import java.util.Stack;

/**
 * TileGame takes a stack and queue of integers 1, 2, or 3 and moves/removes tiles until the stack is empty
 * 
 * @author Alec Porter
 */
public class TileGame {

    /**
     * No argument ocnstructor
     */
    public TileGame(){
    }
    
    /**
     * This method manipulates a stack and queue unitl the stack is empty base on the following logic:
     * If the tile is a 1, you remove that tile plus one more from the top of the stack.
     * If the tile is a 2, you remove that tile plus two more from the top of the stack.
     * If the tile is a 3, you remove that tile but take the three next tiles from the queue and add them to the top of the stack.
     *  
     * @param stack input stack of integers (1, 2, or 3)
     * @param q input queue of integers (1, 2, or 3)
     * @return number of turns to empty the stack
     */
    public int tileGame(Stack<Integer> stack, Queue<Integer> q){

        int numTurns = 0;
        int stackTile;
        int addTile;

        while (!stack.isEmpty()){

            stackTile = stack.peek();  // look at top tile in stack

            if (stackTile == 1){ // top tile is a 1
                stack.pop();  // remove top tile
                numTurns++;
                if (!stack.isEmpty()){ // check if stack is not empty
                    stack.pop(); // remove one additional tile 
                }
            }
            else if (stackTile == 2){ // top tile is a 2
                stack.pop(); // remove top tile
                numTurns++;
                for (int i = 0; i < 2; i++){
                    if (!stack.isEmpty()){ // check if stack is not empty
                        stack.pop(); // remove one additional tile
                    }
                }
            }
            else if (stackTile == 3){
                stack.pop(); // remove top tile
                numTurns++;
                for (int i = 0; i < 3; i++){ 
                    if (!q.isEmpty()){ // check if the queue is not empty
                        addTile = q.poll(); // poll tile from queue and add to stack
                        stack.push(addTile);
                    }
                }
            }

        }

        return numTurns;

    }

}
