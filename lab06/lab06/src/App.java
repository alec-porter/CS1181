import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class App {

    private static int clickCount = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 6");

        // create window for display
        JFrame window = new JFrame("Button Box");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(100, 100);
        window.setSize(400, 400);

        // create jpanel with borderlayout for grid and message
        JPanel panel1 = new JPanel();
        window.setContentPane(panel1);
        panel1.setLayout(new BorderLayout());

        // create jpanel with 3x3 grid for game
        JPanel panel2 = new JPanel();

        panel2.setLayout(new GridLayout(3,3));
        JLabel text = new JLabel();
        text.setText("Player 1's Turn");

        // add grid and label to borderlayout
        panel1.add(panel2, BorderLayout.CENTER);
        panel1.add(text, BorderLayout.SOUTH);


        for (int i = 0; i < 9; i++){
            JButton button = new JButton();
            button.addActionListener(e -> {

                // if the click count is even, it's player 1's turn
                if (clickCount % 2 == 0){
                    
                    if (button.getText().equals("")){
                        button.setText("X"); // label button "X" if it hasn't been clicked
                        clickCount += 1;
                        button.setEnabled(false);  // disable button after it is clicked
                        if (clickCount == 9){
                            text.setText("Game Over"); // print game over if all nine buttons have been pressed
                        }
                        else{
                            text.setText("Player 2's Turn"); // print next player's turn
                        }
                    }
                }
                // if the click count is odd, it's player 2's turn
                else {
                    if (button.getText().equals("")){
                        button.setText("O");  // label button "O" if it hasn't been clicked
                        clickCount += 1;
                        button.setEnabled(false);  // disable button after it is clicked
                        if (clickCount == 9){
                            text.setText("Game Over"); // print game over if all nine buttons have been pressed
                        }
                        else{
                            text.setText("Player 1's Turn"); // print next player's turn
                        }
                    }
                }
            });
            panel2.add(button);  // add buttons to panel 2
        }

        // display window to screen
        window.setVisible(true);
    }
}
