package deliverable3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class View extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 500;

    private static final int AREA_ROWS = 20;
    private static final int AREA_COLUMNS = 40;

    private static final String INPUT_SPECIFIER1 = "\nEnter just six different integers from 1 through 60, separated by one or more spaces:";
    private static final String INPUT_SPECIFIER2 = "\nEnter just one integer from 1 through 100000:";

    private JLabel labelSix;
    private JLabel labelReps;
    private JTextField textSix;
    private JTextField textReps;

    private JButton button;
    private final JTextArea resultArea;

    private final Controller cntl;

    public View(Controller controller) {
        super("Lottery Numbers");
        cntl = controller;
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        resultArea.setText("");

        createTextFieldEnterSixNums();
        createTextFieldEnterReps();

        createButton();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); // centers the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // quits when frame is closed
        //https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
        getRootPane().setDefaultButton(button);
    }

    private void createTextFieldEnterSixNums() {
        labelSix = new JLabel(INPUT_SPECIFIER1);
        final int FIELD_WIDTH = 10;
        textSix = new JTextField(FIELD_WIDTH);
    }
    
    private void createTextFieldEnterReps() {
        labelReps = new JLabel(INPUT_SPECIFIER2);
        final int FIELD_WIDTH = 10;
        textReps = new JTextField(FIELD_WIDTH);
    }
    
    private void createButton() {
        button = new JButton("Run lottery");
        button.addActionListener(event -> showResults(textSix.getText(),textReps.getText()));
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.add(labelSix);
        panel.add(textSix);
        panel.add(labelReps);
        panel.add(textReps);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        panel.add(button);
        add(panel);
    }

    public void displaySelf() {
        this.setVisible(true);
    }

    private void showResults(String input, String reps) {
        resultArea.setText("");
        boolean validInput = cntl.isJustSix(input);
        boolean validRep = cntl.isValidRep(reps);
        
        //  if the user's entries are valid,
        if (validInput && validRep) {
            // print the user's entries
            resultArea.append("You entered the following values: ");
            resultArea.append("\nLottery numbers: " + input.trim());
            resultArea.append("\nNumber of drawings: " + reps.trim() + "\n");
            
            //  convert String reps to int repNum
            int repNum = Integer.parseInt(reps);
            
            //  convert String input to int [] userNums,
            //      by first splitting input into String [] strings,
            String [] strings = input.split(" ");
            
            //  then fill int [] userNums,
            //      by converting each string in String [] strings to an int
            //  Note: Used a for loop rather than a for-each to keep indexes consistent
            int [] userNums = new int[6];
            for (int i = 0; i < strings.length; i++){
                userNums[i] = Integer.parseInt(strings[i]);
            }
            
            //  calls getLottoResults in controller to get int [] results = int [] lottoResults
            int [] lottoResults = cntl.getLottoResults(userNums, repNum);
            
            // iterate through int [] lottoResults,
            for (int i = 0; i < lottoResults.length ; i++){
                //  display each value in lottoResults 
                //  (each value represents the number of lotteries where the user's numbers matched int index responses)
                resultArea.append("\n"+ lottoResults[i] + " drawings matched " + i + " of your numbers.");
            }
        // if the user enters invalid entries, return messages for the reasons they were invalidated
        } else {
            javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),cntl.getInvalidMessage());
            textSix.requestFocus();
        }
    }

}
