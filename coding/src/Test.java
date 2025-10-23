/*n a software engineering team,
 we want to identify the most active developers on GitHub.
By "active," we currently define it as the developers who commit the most code.
You are given an array of developer IDs (ids)
representing commit events in a given timeframe —
each element in the array is a developer ID that corresponds to a single code commit.
Your task is to write a program that returns
 the top k most active developers (i.e., those with the most commits).

 */

// input -[1,2,1,3,1,4,2] k - 2 -> [1,2]

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Integer> values = getKActiveDevelopers(new int[]{1,2,1,3,1,4,2},3);
        for(int i: values){
            System.out.println(i);
        }
    }
    public static List<Integer> getKActiveDevelopers(int[]input, int k){
        HashMap<Integer,Integer> compute = new HashMap<>();
        for(int i: input){
            compute.put(i,compute.getOrDefault(i,0)+1);
        }
        ArrayList<Integer> mostActive = new ArrayList<>();
        //1-3, 2-2, 3-1 , 4-1 2
        LinkedHashMap<Integer, Integer> sortedMap = compute.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function for duplicate keys (not relevant for sorting)
                        LinkedHashMap::new // Use LinkedHashMap to preserve insertion order (which is now sorted)
                ));
        List<Integer> sortedValues = sortedMap.values().stream().toList();
        int size = sortedValues.size();
        for(int i = 1; i <= k ; i++){
            int current = sortedValues.get(size - i);
            for(Map.Entry<Integer,Integer> entry : sortedMap.entrySet()){
                if(entry.getValue() == current){
                    mostActive.add(entry.getKey());
                }
            }
        }
        return mostActive;
    }

    /*

# Given a two-dimensional array,
count paths from the top left to the bottom right,
where you only move right or down.
#
# input: [
#           [a, b, c],
#           [d, e, f]
#        ]
#
2 1
3 2
abcf
abef
adef

- bottomright = [input.length][input[0].length]
- 00
01 10 20
  11 21

- [
#           [a, b, c, d],
#           [d, e, f ,g]
#        ]
# output: 4
    */
    public static int countPathsToBottom(int[][]input){
        int paths = 0;
        paths = countPath(input, 0, 0);
        return paths;
    }

    private static int countPath(int[][] input, int row, int col) {
        int path = 0;
        if(row == input.length - 1  && col == input[0].length - 1){
            return 1;
        }
        if(row < input.length - 1){
            path += countPath(input, row + 1, col);
        }
        if(col < input[0].length - 1){
            path += countPath(input, row, col + 1);
        }
        return path;
    }
}
