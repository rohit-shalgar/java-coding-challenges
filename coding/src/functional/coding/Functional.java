package functional.coding;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {
    public static List<Integer> nonZeroElementsFiltered(List<Integer>input){
        return input.stream()
                .filter(i -> i!= 0)
                .collect(Collectors.toList());
    }

    public static Map<String,Integer> getWordFrequencyCount(String str){
        return Stream.of(str)
                .map(s-> s.split("\\s+"))
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(
                        s->s,
                        s->1,
                        Integer::sum
                ));
    }

    public static int getMelonsWeightSum(List<Melon> melons) {
        return melons.stream()
                .map(Melon::getWeight)
                .reduce(0,Integer::sum);
    }

    public static int getMelonsWeightSumCollectorApi(List<Melon> melons) {
        return melons.stream()
                .collect(Collectors.reducing(0,Melon::getWeight,Integer::sum));
    }


}
