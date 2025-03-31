import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

/**
 * Degree Conversion GUI Class
 * 
 * @author Alec Porter
 */
public class DegreeConvertWindow extends JFrame{

    // initialize variables
    private double fahrenheitValue = 0;
    private double celciusValue = 0;

    public DegreeConvertWindow(String windowTitle) throws Exception{

        super(windowTitle);

        // create Panel, Labels, and TextFields
        JPanel content = new JPanel();
        JLabel fahrenheitTitle = new JLabel("Fahrenheit");
        JLabel celciusTitle = new JLabel("Celcius");
        JTextField fahrenheitInput = new JTextField();
        JTextField celciusOutput = new JTextField();
        JButton buttonConvertTemp = new JButton("Convert");
        DecimalFormat celciusFormat = new DecimalFormat("0.00"); // 

        // use a grid layout to organize labels, text fields, and button
        this.setContentPane(content);
        content.setLayout(new GridLayout(3, 2));
        this.setSize(250, 100);
        this.setLocation(250, 250);

        // listener for button click
        buttonConvertTemp.addActionListener(e -> {
            try{ // if clicked with double data type in farenheit field conver to celcius
                String inputValue = fahrenheitInput.getText();
                fahrenheitValue = Double.parseDouble(inputValue); // convert text field to double
                celciusValue = (fahrenheitValue-32)*5/9;
                String outputValue = celciusFormat.format(celciusValue); // convert double to string
                celciusOutput.setText(outputValue);
            }
            catch(Exception except){
                if (fahrenheitInput.getText().isEmpty()){ // error message if no input is provided
                    String outputValue = "No Input Provided";
                    celciusOutput.setText(outputValue);
                }
                else{
                    String outputValue = "Invalid Input"; // error message if invalid data type is provided
                    celciusOutput.setText(outputValue);
                }
            }
        });


        // add buttons, lables, etc. to JPanel for display
        content.add(fahrenheitTitle);
        content.add(fahrenheitInput);
        content.add(celciusTitle);
        content.add(celciusOutput);
        content.add(buttonConvertTemp);
    }
    
}
