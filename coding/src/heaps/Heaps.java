package heaps;
/*
Binary tree - two nodes
root node
has 2 nodes max
and leaf nodes - leafes w/o nodes
 */

/*
Max heap - largest element at root
Min heap - smallest element at root
in Java heap is implemented using priority queue and by def its min heap
need to reverse it to make it max heap
 */

import java.util.*;

/*

 */
public class Heaps {
    public static ArrayList<Integer> topKActiveDevelopers(int[] input, int k){
        HashMap<Integer,Integer> commitCompute = new HashMap<>();
        for(int i : input){
            commitCompute.compute(i, (key,v) -> v == null ? 1 : v +1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>(
                (a,b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(commitCompute.entrySet());
        ArrayList<Integer> topKActiveDevelopers = new ArrayList<>();
        for(int i = 1; i <= k && !maxHeap.isEmpty(); i++){
            topKActiveDevelopers.add(maxHeap.poll().getKey());
        }
        return topKActiveDevelopers;
    }

    public String[] findRelativeRanks(int[] score) {
        HashMap<Integer,Integer> relativeIndexesCompute = new HashMap<>();
        for(int i = 0; i < score.length; i++){
            relativeIndexesCompute.put(score[i],i);
        }
        Arrays.sort(score);
        String[] relativeRanks = new String[score.length];
        for(int i = 0; i < score.length ; i++){
            int sortedScore = score[score.length - 1 - i];
            int indexToBeUpdated = relativeIndexesCompute.get(sortedScore);
            if(i == 0){relativeRanks[indexToBeUpdated] = "Gold Medal";}
            else if(i == 1){relativeRanks[indexToBeUpdated] = "Silver Medal";}
            else if(i == 2){relativeRanks[indexToBeUpdated] = "Bronze Medal";}
            else{
                relativeRanks[indexToBeUpdated] = String.valueOf(i+1);
            }
        }
        return relativeRanks;
    }

    public static void main(String[] args) {
        List<Integer> values = topKActiveDevelopers(new int[]{1,2,1,3,1,4,2},1);
        for(int i: values){
            System.out.println(i);
        }
    }


    static class MinHeap {
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            // Write your code here.
            return new ArrayList<Integer>();
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            // Write your code here.
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            // Write your code here.
        }

        public int peek() {
            // Write your code here.
            return -1;
        }

        public int remove() {
            // Write your code here.
            return -1;
        }

        public void insert(int value) {
            // Write your code here.
        }
    }
}
