public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1181 Lab 5");

        // create degree converter window, exit on close, and set visible
        DegreeConvertWindow degreeConverter = new DegreeConvertWindow("Temperature Converter");
        degreeConverter.setDefaultCloseOperation(DegreeConvertWindow.EXIT_ON_CLOSE);
        degreeConverter.setVisible(true);

    }
}
