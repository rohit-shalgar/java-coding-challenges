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

    public static int[] findThreeLargestNumbers(int[] array) {
        // Write your code here.
        int k = 3;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i : array){
            minHeap.offer(i);
            if(minHeap.size() > 3){
                minHeap.poll();
            }
        }
        int[] theLargestNumbers = new int[k];
        for(int i = 0; i < k ; i++){
            theLargestNumbers[i] = minHeap.poll();
        }
        return theLargestNumbers;
    }

    // similiar to topK devs but done in o(n) time and o(n) space using bucket sort
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer>topKFrequentCompute =
                new HashMap<>();
        for(int i : nums){
            topKFrequentCompute.compute(i,
                    (key,value) -> value == null? 1 : value + 1
            );
        }
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        for(int key: topKFrequentCompute.keySet()){
            int frequency = topKFrequentCompute.get(key);
            if(bucket[frequency] == null){
                bucket[frequency] = new ArrayList<Integer>();
            }
            bucket[frequency].add(key);
        }
        List<Integer> topKFrequentList = new ArrayList<Integer>();
        for(int i = bucket.length - 1 ; i >= 0 && topKFrequentList.size() < k; i--){
            if(bucket[i] != null){
                topKFrequentList.addAll(bucket[i]);
            }
        }
        int[]topKFrequent = new int[topKFrequentList.size()];
        for(int i = 0 ; i < topKFrequent.length; i++){
            topKFrequent[i] = topKFrequentList.get(i);
        }
        return topKFrequent;
    }

    //retry and see other solutions also to better understand the logic for faster code
    public int nthUglyNumber(int n) {
        int[] primeFactors = {2,3,5};
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        HashSet<Long> lastSeen = new HashSet<>();
        minHeap.offer(1L);
        lastSeen.add(1L);
        long current = 1L;
        for(int i = 0; i < n; i++){
            current = minHeap.poll();
            for(int prime: primeFactors){
                long nextSeqNumber = current * prime;
                if(lastSeen.add(nextSeqNumber)){
                    minHeap.offer(nextSeqNumber);
                }
            }
        }
        return (int)current;
    }

    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a,b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]
        );
        for(int i = 0; i < nums.length; i++){
            minHeap.offer(new int[]{nums[i],i});
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        List<int[]> topKNumbers = new ArrayList<>(minHeap);
        topKNumbers.sort((a,b) -> a[1] - b[1]);
        int[]maxSubsequence  = new int[k];
        for(int i = 0; i < k; i++){
            maxSubsequence[i] = topKNumbers.get(i)[0];
        }
        return maxSubsequence;
    }

    public int[] kWeakestRowsSpaceOpt(int[][] mat, int k) {
        int rowLength = mat.length;
        int colLength = mat[0].length;
        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue()? a.getValue() - b.getValue() : a.getKey() - b.getKey()
        );
        for(int row = 0; row < rowLength; row++){
            int noOfSoldiers = 0;
            for(int col = 0; col < colLength; col++){
                if(mat[row][col] == 1){
                    noOfSoldiers += 1;
                }
            }
            minHeap.add(Map.entry(row,noOfSoldiers));
        }
        int[] kWeakestRows = new int[k];
        int i = 0;
        while(i < k && !minHeap.isEmpty()){
            kWeakestRows[i] = minHeap.poll().getKey();
            i++;
        }
        return kWeakestRows;
    }

    //leetcode
    public int[] kWeakestRows(int[][] mat, int k) {
        int rowLength = mat.length;
        int colLength = mat[0].length;
        HashMap<Integer,Integer> weakestRowCompute = new HashMap<>();
        for(int row = 0; row < rowLength; row++){
            int noOfSoldiers = 0;
            for(int col = 0; col < colLength; col++){
                if(mat[row][col] == 1){
                    noOfSoldiers += 1;
                }
            }
            weakestRowCompute.put(row, noOfSoldiers);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue()? a.getValue() - b.getValue() : a.getKey() - b.getKey()
        );
        minHeap.addAll(weakestRowCompute.entrySet());
        int[] kWeakestRows = new int[k];
        int i = 0;
        while(i < k && !minHeap.isEmpty()){
            kWeakestRows[i] = minHeap.poll().getKey();
            i++;
        }
        return kWeakestRows;
    }

    //leetcode
    public int maxProduct(int[] nums) {
        int maxValue1 = 0, maxValue2 = 0;
        for(int i = 0; i < nums.length; i++){
            int current = nums[i];
            if(current > maxValue1){
                int temp = maxValue1;
                maxValue1 = current;
                if(temp > maxValue2){
                    maxValue2 = temp;
                }
            }else if(current > maxValue2){
                maxValue2 = current;
            }
        }
        return (maxValue1 - 1) * (maxValue2 - 1);
    }

    //leetcode
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1 ){
            return stones[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (a,b) -> b - a
        );
        for(int i : stones){
            maxHeap.add(i);
        }
        while(maxHeap.size() > 1){
            int stoneDiff = maxHeap.poll() - maxHeap.poll();
            if(stoneDiff != 0){
                maxHeap.add(stoneDiff);
            }
        }
        return maxHeap.isEmpty() ? 0: maxHeap.poll();
    }

    //leetcode
    class KthLargest {
        private int k;
        private List<Integer>sortedList = new ArrayList<>();

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for(int i: nums){
                insertIntoSortedList(i);
            }
        }

        public int add(int val) {
            insertIntoSortedList(val);
            return sortedList.get(k - 1);
        }

        private void insertIntoSortedList(int i){
            int index = Collections.binarySearch(sortedList,i,Comparator.reverseOrder());
            if(index < 0) index = -(index) - 1;
            sortedList.add(index,i);
        }
    }
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
