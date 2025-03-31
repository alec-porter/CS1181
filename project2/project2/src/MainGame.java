import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Class MainGame - my game for CS1181 Porject 2
 * 
 * @author Alec Porter
 */
public class MainGame {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1181 Project 2");

        // create pause game object
        Pause pauseGame = new Pause();

        // create jframe and new dialog box with instructions
        JFrame instructionWindow = new JFrame("Instructions");
        JDialog dialog = new Instructions(instructionWindow, "Instructions", true);
        pauseGame.setPauseTime(1000);

        // create main game window at set size and not resizeable
        JFrame mainGameWindow = new JFrame("Target Tracker");
        mainGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameWindow.setSize(500, 550);
        mainGameWindow.setLocation(50, 50);
        mainGameWindow.setResizable(false);

        // create jpanel with border layout
        JPanel mainGameContent = new JPanel();
        mainGameWindow.setContentPane(mainGameContent);
        mainGameContent.setLayout(new BorderLayout());
        
        // create target area and add to main game window jpanel
        TargetArea targetArea = new TargetArea();
        mainGameContent.add(targetArea, BorderLayout.CENTER);
        JLabel gameStatusLabel = new JLabel("Target in Range:  Fire at Will");
        Font gameStatusFont = new Font("Arial", Font.BOLD, 20);
        gameStatusLabel.setFont(gameStatusFont);
        gameStatusLabel.setForeground(Color.BLUE);
        gameStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainGameContent.add(gameStatusLabel, BorderLayout.SOUTH);
        mainGameWindow.setVisible(true);

        // mouse y-coord includes title bar, set offset to align mouse y-axis with target y-axis
        int targetYOffset = mainGameWindow.getInsets().top; 
        
        // create score object
        CalculateHit score = new CalculateHit(0, 0, 0, 0, 0, 0);

        // create score window
        JFrame scoreWindow = new JFrame("Score");
        JPanel scoreContent = new JPanel();
        scoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scoreWindow.setSize(200,225);
        scoreWindow.setLocation(555, 50);
        scoreWindow.setResizable(false);
        scoreWindow.setVisible(true);
        scoreWindow.setContentPane(scoreContent);
        scoreContent.setLayout(new GridLayout(13,2));
        JLabel mouseCoordTitle = new JLabel("Current Coord");
        scoreContent.add(mouseCoordTitle);
        JLabel mouseValueTitle = new JLabel("Value");
        scoreContent.add(mouseValueTitle);
        JLabel mouseXTitle = new JLabel("X Coordinate:");
        scoreContent.add(mouseXTitle);
        JLabel mouseXCoord = new JLabel();
        scoreContent.add(mouseXCoord);
        JLabel mouseYTitle = new JLabel("Y Coordinate:");
        scoreContent.add(mouseYTitle);
        JLabel mouseYCoord = new JLabel();
        scoreContent.add(mouseYCoord);
        JLabel separator1 = new JLabel("------------------");
        scoreContent.add(separator1);
        JLabel separator2 = new JLabel("------------------");
        scoreContent.add(separator2);
        JLabel shotCoordTitle = new JLabel("Strike Coord");
        scoreContent.add(shotCoordTitle);
        JLabel shotCoordValue = new JLabel("Value");
        scoreContent.add(shotCoordValue);
        JLabel shotXTitle = new JLabel("X Coordinate:");
        scoreContent.add(shotXTitle);
        JLabel shotXCoord = new JLabel();
        scoreContent.add(shotXCoord);
        JLabel shotYTitle = new JLabel("Y Coordinate:");
        scoreContent.add(shotYTitle);        
        JLabel shotYCoord = new JLabel();
        scoreContent.add(shotYCoord);
        JLabel targetHitTitle = new JLabel("Target Hit:");
        scoreContent.add(targetHitTitle);
        JLabel targetHitValue = new JLabel();
        scoreContent.add(targetHitValue);
        JLabel separator3 = new JLabel("------------------");
        scoreContent.add(separator3);
        JLabel separator4 = new JLabel("------------------");
        scoreContent.add(separator4);  
        JLabel scoreTitle = new JLabel("Total Hits:");
        scoreContent.add(scoreTitle);
        JLabel scoreValue = new JLabel();
        scoreContent.add(scoreValue);
        JLabel timesUp = new JLabel();
        scoreContent.add(timesUp);
        
        
        // inner class to implement mouselisten to record mouse click coordinates and display hit status
        class Mouse extends Frame implements MouseListener{
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();  // get mouse x coord
                int mouseY = e.getY();  // get mouse y coord
                int targetX = targetArea.getTargetXPosition();  // get target x-coord
                int targetY = targetArea.getTargetYPosition();  // get target y-coord
                int targetSize = targetArea.getTargetSize();  // get target size (height = width)
                
                // set variables in score class to determine if mouse click was in target area
                score.setMouseXCoord(mouseX);
                score.setTargetXCoord(targetX);
                score.setMouseYCoord(mouseY);
                score.setTargetYCoord(targetY);
                score.setTargetSize(targetSize);
                score.setTargetYOffset(targetYOffset);
                boolean validHit = score.getValidHit();

                // print mouse location and hit status to score window
                shotXCoord.setText("" + mouseX);
                shotYCoord.setText("" + (mouseY - targetYOffset));
                targetHitValue.setText("" + validHit);
                scoreValue.setText("" + score.getNumOfHits());
            }
            @Override
            public void mouseEntered(MouseEvent e){}  // this interface not required
            @Override
            public void mouseExited(MouseEvent e){}  // this interface not required
            @Override
            public void mousePressed(MouseEvent e){}  // this interface not required
            @Override
            public void mouseReleased(MouseEvent e){}  // this interface not required
        }
        // create mouse click listener
        Mouse mouseClick = new Mouse();
        mainGameWindow.addMouseListener(mouseClick);

        // inner class to track mouse position in the game window
        class MouseX extends Frame implements MouseMotionListener{
            @Override
            public void mouseMoved(MouseEvent e){
                int mouseX = e.getX();
                int mouseY = e.getY();
                mouseXCoord.setText("" + mouseX);
                mouseYCoord.setText("" + (mouseY - targetYOffset));
            }
            @Override
            public void mouseDragged(MouseEvent e){} // this interface not required
        }
        // create mouse movement listener
        MouseX mouseMove = new MouseX();
        mainGameWindow.addMouseMotionListener(mouseMove);

        // move target around play area
        targetArea.targetMovement();  

        // target movement complete, update status messages
        gameStatusLabel.setForeground(Color.RED);
        gameStatusLabel.setText("Target Out of Range, Cease Fire");
        timesUp.setForeground(Color.RED);
        timesUp.setText("Target Lost");

        // pause game 
        pauseGame.setPauseTime(2000);
        
        // clear main game window and target window
        mainGameWindow.setVisible(false);
        scoreWindow.setVisible(false);

        // display final score window then exit program
        JFrame finalScore = new JFrame("Final Score");
        finalScore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finalScore.setVisible(true);
        finalScore.setSize(500, 200);
        finalScore.setLocation(100, 100);
        finalScore.setResizable(false);
        JPanel finalScoreContent = new JPanel();
        finalScore.setContentPane(finalScoreContent);
        finalScoreContent.setLayout(new GridLayout(1,1));
        JLabel finalScoreValue = new JLabel ("Your Score: " + score.getNumOfHits());
        Font font = new Font("Arial", Font.BOLD, 40);
        finalScoreValue.setFont(font);
        finalScoreValue.setForeground(Color.BLUE);
        finalScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
        finalScoreContent.add(finalScoreValue);
        pauseGame.setPauseTime(3000);
        System.exit(0);
        
    }
}
