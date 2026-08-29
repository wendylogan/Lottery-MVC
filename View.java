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

    // Cleaner, user-friendly labels
    private static final String LABEL_NUMBERS = "Your 6 Numbers (1-60):";
    private static final String LABEL_DRAWS = "Number of Draws (1-100,000):";

    private JLabel labelSix;
    private JLabel labelReps;
    private JTextField textSix;
    private JTextField textReps;

    private JButton button;
    private final JTextArea resultArea;

    private final Controller cntl;

    public View(Controller controller) {
        super("Lottery Simulator");
        cntl = controller;
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        resultArea.setText("");

        createTextFieldEnterSixNums();
        createTextFieldEnterReps();
        createButton();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(button);
    }

    private void createTextFieldEnterSixNums() {
        labelSix = new JLabel(LABEL_NUMBERS);
        final int FIELD_WIDTH = 10;
        textSix = new JTextField(FIELD_WIDTH);
        textSix.setToolTipText("Enter 6 numbers between 1 and 60, separated by spaces");
    }
    
    private void createTextFieldEnterReps() {
        labelReps = new JLabel(LABEL_DRAWS);
        final int FIELD_WIDTH = 10;
        textReps = new JTextField(FIELD_WIDTH);
        textReps.setToolTipText("Enter how many times to run the lottery (1-100,000)");
    }
    
    private void createButton() {
        button = new JButton("Run Lottery");
        button.addActionListener(event -> showResults(textSix.getText(), textReps.getText()));
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
        
        if (validInput && validRep) {
            resultArea.append("You entered the following values:\n");
            resultArea.append("  Numbers: " + input.trim() + "\n");
            resultArea.append("  Draws:   " + reps.trim() + "\n\n");
            
            int repNum = Integer.parseInt(reps);
            String[] strings = input.split("\\s+");
            int[] userNums = new int[6];
            for (int i = 0; i < strings.length; i++){
                userNums[i] = Integer.parseInt(strings[i]);
            }
            
            int[] lottoResults = cntl.getLottoResults(userNums, repNum);
            
            resultArea.append("=== Match Results ===\n");
            for (int i = 0; i < lottoResults.length; i++){
                resultArea.append("  " + lottoResults[i] + " drawing(s) matched " + i + " of your numbers.\n");
            }
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                cntl.getInvalidMessage()
            );
            textSix.requestFocus();
        }
    }
}