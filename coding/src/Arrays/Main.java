package Arrays;

import Arrays.Algorithms.coding.Arrays;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //checkTwoNumberSum();
        //checkTwoNumberSumOptimized();
        //validateSequence();
        //validateSequenceOpt();
        sortedSquaredArray();
        sortedSquaredArrayOpt();

    }

    @Test
    public void TestCase1() {
        int[][] input = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] expected = new int[][] {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] actual =new Arrays().transposeMatrix(input);
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                Assert.assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    private static void sortedSquaredArrayOpt() {
        int[] output = Arrays.sortedSquaredArrayOpt(new int[]{-10,1,2,3,5,6,8});
        for(int i: output){
            System.out.println(i);
        }
    }

    private static void sortedSquaredArray() {
        int[] output = Arrays.sortedSquaredArray(new int[]{-10,1,2,3,5,6,8});
        for(int i: output){
            System.out.println(i);
        }
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
