package Strings;

import Strings.Algorithms.coding.Arrays;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //checkTwoNumberSum();
        //checkTwoNumberSumOptimized();
        validateSequence();
        validateSequenceOpt();
    }

    private static void validateSequenceOpt() {
        List<Integer>array = List.of(5,1,22,25,6,-1,8,10);
        List<Integer>sequence = List.of(1,6,-1,10);
        System.out.println(Arrays.validateSubSequenceOptimized(array,sequence));
    }

    private static void validateSequence() {
        List<Integer>array = List.of(5,1,22,25,6,-1,8,10);
        List<Integer>sequence = List.of(1,6,-1,10);
        System.out.println(Arrays.validateSubSequence(array,sequence));
    }

    private static void checkTwoNumberSum() {
        final int[] indices = Arrays.twoNumberSum(new int[]{3,5,-4,8,11,1,-1,6},10);
        for(int indice:indices) {
            System.out.println(indice);
        }
    }

    private static void checkTwoNumberSumOptimized() {
        final int[] indices = Arrays.twoNumberSumOptimized(new int[]{3,5,-4,8,11,1,-1,6},10);
        for(int indice:indices) {
            System.out.println(indice);
        }
    }
}
