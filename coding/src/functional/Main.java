package functional;

import functional.coding.Functional;
import functional.coding.Melon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
      //  filterNonZeroElements();
        //wordFrequencyCount();
        List<Melon> melons = Arrays.asList(new Melon("Gac", 2000),
                new Melon("Hemi", 1600), new Melon("Gac", 3000),
                new Melon("Apollo", 2000), new Melon("Horned", 1700));

        int melonWeightsInGrams = Functional.getMelonsWeightSum(melons);
        System.out.println("Sum of all the melon weights is ->"+melonWeightsInGrams);

    }

    private static void wordFrequencyCount() {
        Map<String,Integer> map = Functional.getWordFrequencyCount("orem Ipsum is simply \n" +
                "    Ipsum Lorem not simply Ipsum");
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            System.out.println("key:"+entry.getKey());
            System.out.println("value"+entry.getValue());
        }
    }

    private static void filterNonZeroElements() {
        List<Integer> ints = Arrays.asList(1, 2, -4, 0, 2, 0, -1, 14, 0, -1);
        for(int i: Functional.nonZeroElementsFiltered(ints)){
            System.out.println(i);
        }
    }
}
