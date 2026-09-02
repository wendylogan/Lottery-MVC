import java.util.ArrayList;
import java.util.List;

public class Model {

    // Stores result in List
    private List<Integer> lottoResults;
 
    public Model() {
        lottoResults = new ArrayList<>();
    }

    public List<Integer> doLottoDrawing() {
        // Condition: users' numbers are already validated
        // Returns a list of the 6 integers drawn in the lottery
        this.lottoResults = new ArrayList<>();
        int drawing;
        while (this.lottoResults.size()<6){
            drawing = (int)((Math.random() * 59) + 1);
            if (!this.lottoResults.contains(drawing)){
                this.lottoResults.add(drawing);
            }
        }
        return this.lottoResults;
    }
}
