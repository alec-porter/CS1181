import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Class Instructions - provides instructions to player on how to play game
 * 
 * @author Alec Porter
 */
public class Instructions extends JDialog{

    /**
     * Constructor to display instructions as a jdialog box
     * 
     * @param owner parent frame the dialog box is attached to
     * @param title title of the dialog box
     * @param modal captures focus until dialog box is closed
     */
    public Instructions(JFrame owner, String title, boolean modal){
        super(owner, title, modal);
    
        // set layout of the dialog box
        this.setLayout(new GridLayout(16,1));
        this.setSize(900,350);
        this.setLocation(50,50);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // instruction labels
        JLabel titleText = new JLabel("Instructions for Target");
        JLabel blank1 = new JLabel("     ");
        JLabel overview = new JLabel("The goal of this game is to strike a moving target as may times as you can before it is out of range.");
        JLabel inst1 = new JLabel("The target is indicated by the black square.");
        JLabel inst2 = new JLabel("To strike the target you click on the black square using your mouse.");
        JLabel inst3 = new JLabel("Each successful strike on the target results in a score of one point.");
        JLabel inst4 = new JLabel("The target area opens in a new window and you have a limited time to strike the target before it is out of range.");
        JLabel inst5 = new JLabel("A score window displays your cursor coordinates, the coordinates of each strike made and if you hit the target on that strike, and your score.");
        JLabel inst6 = new JLabel("When time runs out the target area window and score window will automatically close.");
        JLabel inst7 = new JLabel("Your final score will open in a new window and then close automatically after a few seconds.");
        JLabel inst8 = new JLabel("When you are ready to begin click the start button.");
        JLabel inst9 = new JLabel("The instruction window will close automatically.");
        JLabel inst10 = new JLabel("There will be a short pause after this window closes and before target window opens in the top left corner of your screen.");
        JLabel inst11 = new JLabel("Note: striking the target works best if the mouse is not moving. If the mouse is moving when the button is pressed it may not register the strike.");
        JLabel blank2 = new JLabel("Anticipate where the target is moving and strike it as many times as you can while the mouse is stationary to rack up the points!");
        JButton startButton = new JButton("Start Game");

        // change dialog box state when button is clicked
        startButton.addActionListener(e -> {
            this.setVisible(false);
        });

        // add elements to dialog box
        this.add(titleText);
        this.add(blank1);
        this.add(overview);
        this.add(inst1);
        this.add(inst2);
        this.add(inst3);
        this.add(inst4);
        this.add(inst5);
        this.add(inst6);
        this.add(inst7);
        this.add(inst8);
        this.add(inst9);
        this.add(inst10);
        this.add(inst11);
        this.add(blank2);
        this.add(startButton);
        this.setVisible(true);

    }
}
