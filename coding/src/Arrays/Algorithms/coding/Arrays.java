package Strings.Algorithms.coding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}

