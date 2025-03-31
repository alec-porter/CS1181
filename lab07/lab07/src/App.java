import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 7");

        // create jframe and jpanel
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setLocation(100, 100);
        window.setSize(500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel();
        window.setContentPane(content);

        ComponentList<JLabel> colorList = new ComponentList<>(new ArrayList<>(Arrays.asList(
            new JLabel("Red"),
            new JLabel("Blue"),
            new JLabel("Green"),
            new JLabel("Yellow")
            )));

        colorList.add(new JLabel("Orange"));
        colorList.setComponentAtIndex(0, new JLabel("Maroon"));

        window.add(colorList);



    }
}
