package heaps;

import java.util.*;

public class HeapsRepractice {
    public static ArrayList<Integer> topKActiveDevelopers(int[] input, int k) {
        HashMap<Integer,Integer> topActiveCompute = new HashMap<>();
        for(int i: input){
            topActiveCompute.compute(i,(
                    key,value)-> value == null ? 1 : value + 1
            );
        }
        PriorityQueue<Map.Entry<Integer,Integer>>maxHeap = new PriorityQueue<>(
                (a,b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(topActiveCompute.entrySet());
        ArrayList<Integer>topKActiveDevelopers = new ArrayList<>();
        for(int i = 1; i <= k && !maxHeap.isEmpty() ; i++){
            topKActiveDevelopers.add(maxHeap.poll().getKey());
            ;
        }
        return topKActiveDevelopers;
    }

    public static void main(String[] args) {
        List<Integer> values = topKActiveDevelopers(new int[]{1,2,1,3,1,4,2},4);
        for(int i: values){
            System.out.println(i);
        }
    }
}
