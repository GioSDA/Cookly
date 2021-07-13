package ly.cook.cookly.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Nutrients {

    private double calories;
    private double glycemicIndex;
    private double totalWeight;

    private List<String> dietLabels;
    private List<String> healthLabels;
    private List<String> cautions;

    private HashMap<String, String> totalNutrients;
    private HashMap<String, String> totalDaily;

}
