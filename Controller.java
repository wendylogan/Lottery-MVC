import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private final Model model;
    private final View view;
    // Stores the error messages, isValid is the only time we have to iterate for those 3 checks
    private String invalidMessage;
    private int[] results;

    public Controller() {
        model = new Model();
        view = new View(this);
        this.invalidMessage = "";
        this.results = new int[7];
    }
     
    public void showView() {
        view.displaySelf();
    }
    
    public boolean isJustSix(String userNums) {
        // NOT EXACTLY 6 NUMBERS
        // Checks if there are exactly 5 sets of (digit then space(s)) then 1 digit, followed by 0 or more digits
        boolean justSix = userNums.matches("(\\d+\\s+){5}\\d+\\s*");
        if (!justSix){this.invalidMessage+= "- Please enter exactly 6 numbers between 1 and 60, separated by spaces.\n";}
        
        // OUT OF BOUNDS and/or INVALID ENTRY
        boolean allNums = true;
        boolean inBounds = true; 
        ArrayList<String> nums = new ArrayList<>(Arrays.asList(userNums.split("\\s+")));
        for (String num:nums){
            // Check if value is a digit before attempting to parseInt
            if (!num.matches("\\d+")){
                allNums = false;
                this.invalidMessage+= "- All entries must be whole numbers (integers).\n";
            }
            // If able to parse to int, check if in range
            else{
                Integer integerNumber = Integer.parseInt(num);
                if (integerNumber > 60 || integerNumber < 1){inBounds = false;}
            }
             
         }
        if (!inBounds){this.invalidMessage+= "- Each number must be between 1 and 60.\n";}
        
        // HANDLE DUPLICATES
        boolean unique = true;
        for (String num : nums){
            if (nums.lastIndexOf(num) != nums.indexOf(num)){unique = false;}
         } 
        if (!unique){this.invalidMessage+= "- Please enter 6 unique numbers (no duplicates).\n";}
        
        return (justSix & inBounds & unique & allNums);
    }
     public boolean isValidRep(String reps) {
         // Single check to see if entry for reps is valid
          if (!reps.matches("([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9]|100000)")){
              this.invalidMessage+="- Please enter a number between 1 and 100,000 for number of draws.\n";
              return false;
          }
          return true;
    }
    
    public String getInvalidMessage(){
        String temp = this.invalidMessage;
        // Resets String once user checks the validity
        this.invalidMessage = "";
        return temp;
    }
    
    public int [] getLottoResults(int [] userNums, int reps) {
        /* 
        - Calls model doLottoDrawing() to perform drawing (to get a list of the winning numbers)
        - Compares the winning numbers with the userNums
        - returns int [] where the ints index corresponds with the number of lottery results matched
        */        
        List<Integer> thisDrawing;
        int repNum = 1;
        int numsMatched = 0;
        this.results = new int [6];
        // For each repetition, where draws = reps
        while (repNum<=reps){
            // Draw a lottery
            thisDrawing  = model.doLottoDrawing();
            // For each value in the lottery, check if contains userNums 
            for (int n = 0; n < userNums.length; n++){
                if (thisDrawing.contains(userNums[n])){
                    numsMatched++;
                }
            }
            this.results[numsMatched]++;
            numsMatched = 0;
            repNum++;
        }
        return this.results;
    }
}
