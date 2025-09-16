package Arrays.Algorithms.coding;

import java.util.*;

public class Arrays {
    public static int[] twoNumberSum(int[]array , int sum){
        for(int i= 0; i < array.length;i++){
            for(int j = 0 ; j < array.length; j++){
                if(i!= j && array[i] + array[j] == sum){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }

    public static int[] twoNumberSumOptimized(int[]array , int targetSum){
        final Set<Integer> compute = new HashSet<>();
        for(int i:array){
            int compliment = targetSum - i;
            if(compute.contains(compliment)){
                return new int[]{i,compliment};
            }
            compute.add(i);
        }
        return new int[]{0,0};
    }

    public static boolean validateSubSequence(List<Integer>array, List<Integer>sequence){
        int seqIndex = 0;
        for(int seq:sequence){
            for(int i:array){
                if(seq == i){
                    seqIndex++;
                }
                if(seqIndex == sequence.size()){
                    break;
                }
            }
        }
        return seqIndex == sequence.size();
    }

    public static boolean validateSubSequenceOptimized(List<Integer>array, List<Integer>sequence){
        int seqIndex = 0;
            for(int i:array){
                if(i == sequence.get(seqIndex)){
                    seqIndex++;
                }
                if(seqIndex == sequence.size()){
                    break;
                }
        }
        return seqIndex == sequence.size();
    }

    public static int[] sortedSquaredArray(int[] array) {
        final int[] squared = new int[array.length];
        for(int i= 0; i < array.length; i++){
            int element = array[i];
            squared[i] = element * element;
        }
        java.util.Arrays.sort(squared);//o(nlogn)
        return squared;
    }

    public static int[] sortedSquaredArrayOpt(int[] array) {
        final int[] squared = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        int arrIndex = array.length - 1;
        while(left <= right && arrIndex >= 0){
            int leftSquared = array[left] * array[left];
            int rightSquared = array[right] * array[right];
            if(leftSquared > rightSquared){
                squared[arrIndex] = leftSquared;
                left++;
            }
            else {
                squared[arrIndex] = rightSquared;
                right--;
            }
            arrIndex--;
        }

        return squared;
    }

    public int[][] transposeMatrix(int[][] matrix) {
        final int[][]transposed = new int[matrix[0].length][matrix.length];
        for(int row = 0; row < matrix[0].length; row++){
            for(int col = 0; col < matrix.length; col++){
                transposed[row][col] = matrix[col][row];
            }
        }
        return transposed;
    }

    /*public List<Integer> threeNumberSum(int[]array, int sum){
        final List<Integer>
    }*/

    public int firstDuplicateValue(int[] array) {
        HashSet<Integer> seen = new HashSet<>();
        for(int i :array){
            if(seen.contains(i)){
                return i;
            }
            seen.add(i);
        }
        return -1;
    }

    public int firstDuplicateValueBrute(int[] array) {
        int minDuplicateIndex = array.length;
        for(int i = 0; i < array.length; i++){
            int first = array[i];
            for(int j = 0; j < array.length; j++){
                if(i!= j && first == array[j]){
                    minDuplicateIndex = Math.min(minDuplicateIndex,j);
                }
            }
        }
        if(minDuplicateIndex == array.length){
            return -1;
        }
        return array[minDuplicateIndex];
    }

    public int[][] mergeOverlappingIntervals(int[][] intervals) {
       List<int[]> mergedIntervals = new ArrayList<>();
       int[][] sortedIntervals = intervals.clone();
        java.util.Arrays.sort(sortedIntervals,(a,b)->Integer.compare(
                a[0],b[0]
        ));
        int[] currentInterval = sortedIntervals[0];
        mergedIntervals.add(currentInterval);
        for(int[] nextInterval : sortedIntervals){
            int currentIntervalEnd = currentInterval[1];
            int nextIntervalStart = nextInterval[0];
            int nextIntervalEnd = nextInterval[1];
            if(currentIntervalEnd >= nextIntervalStart){
                currentInterval[1] = Math.max(currentIntervalEnd, nextIntervalEnd);
            }
            else{
                currentInterval = nextInterval;
                mergedIntervals.add(currentInterval);
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}

