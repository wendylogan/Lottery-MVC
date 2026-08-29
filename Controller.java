import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private final Model model;
    private final View view;
    // stores the error messages, so isValid is the only time we have to iterate for those 3 checks
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
        // checks if there are exactly 5 sets of (digit then space(s)) then 1 digit, followed by 0 or more digits
        boolean justSix = userNums.matches("(\\d+\\s+){5}\\d+\\s*");
        if (!justSix){this.invalidMessage+= "- Please enter exactly 6 numbers between 1 and 60, separated by spaces.\n";}
        
        // OUT OF BOUNDS and/or INVALID ENTRY
        boolean allNums = true;
        boolean inBounds = true; 
        ArrayList<String> nums = new ArrayList<>(Arrays.asList(userNums.split("\\s+")));
        for (String num:nums){
            // check if value is a digit before attempting to parseInt
            if (!num.matches("\\d+")){
                allNums = false;
                this.invalidMessage+= "- All entries must be whole numbers (integers).\n";
            }
            // if able to parse to int, check if in range
            else{
                Integer integerNumber = Integer.parseInt(num);
                if (integerNumber > 60 || integerNumber < 1){inBounds = false;}
            }
             
         }
        if (!inBounds){this.invalidMessage+= "- Each number must be between 1 and 60.\n";}
        
        // DUPLICATES
        boolean unique = true;
        for (String num : nums){
            if (nums.lastIndexOf(num) != nums.indexOf(num)){unique = false;}
         } 
        if (!unique){this.invalidMessage+= "- Please enter 6 unique numbers (no duplicates).\n";}
        
        return (justSix & inBounds & unique & allNums);
    }
     public boolean isValidRep(String reps) {
         // single check to see if entry for reps is valid
          if (!reps.matches("([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9]|100000)")){
              this.invalidMessage+="- Please enter a number between 1 and 100,000 for repetitions.\n";
              return false;
          }
          return true;
    }
    
    public String getInvalidMessage(){
        String temp = this.invalidMessage;
        // resets String once user checks the validity
        // previous, accumulated error messages from each check in the run
        this.invalidMessage = "";
        return temp;
    }
    
    public int [] getLottoResults(int [] userNums, int reps) {
        /* 
            calls model doLotto (to get a list of the winning numbers
            compares the winning numbers with the userNums
            
        */        
// calls model to  perform drawing, 
        // returns int [] where the ints index corresponds with
        // the number of lottery results matched
        // lottery numbers matched
        List<Integer> thisDrawing;
        int repNum = 1;
        int numsMatched = 0;
        this.results = new int [6];
        // for each repetition (reps)
        while (repNum<=reps){
            // draw a lottery
            thisDrawing  = model.doLottoDrawing();
            // for each value in the lottery, check if the userNums 
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
