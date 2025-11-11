package BinarySearch;

import java.util.PriorityQueue;

public class Code {

    public static int[] searchForRange(int[] array, int target) {
        int startIdx = -1;
        int endIdx = -1;
        boolean isTargetFound = false;
        for(int i = 0; i < array.length ; i++){
            int value = array[i];
            if(!isTargetFound && value == target){
                startIdx = i;
                endIdx = i;
                isTargetFound = true;
            } else if (isTargetFound && value == target) {
                endIdx = i;
            }
        }
        // Write your code here.
        return new int[] {startIdx, endIdx};
    }

    /*
    {
      "array": [0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73],
      "target": 45
    }
     */
    public static int[] searchForRangeOpt(int[] array, int target) {
        int[] range = new int[]{-1,-1};
        range[0] = modifiedBinarySearch(array,target,0,array.length - 1,true);
        range[1] = modifiedBinarySearch(array,target,0,array.length - 1, false);
        return range;
    }

    private static int modifiedBinarySearch(int[] array, int target, int left,int right, boolean goLeft) {
        while(left <= right){
            int mid = (left + right) / 2;
            if(array[mid] > target){
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            }else {
                if(goLeft){
                    if(mid == 0 || array[mid - 1] != target){
                        return mid;
                    }else {
                        right = mid - 1;
                    }
                }else {
                    if(mid == array.length - 1 || ((mid + 1 <= array.length - 1) &&
                        array[mid + 1] != target)){
                        return mid;
                    }
                    else {
                        left = mid + 1;
                    }
                }
            }
        }
        return -1;
    }


    public static int shiftedBinarySearch(int[] array, int target) {
        int left1 = 0, right1 = 0, left2 = 0, right2 = array.length;
        int targetIndex = -1;
        int index = 1;
        while(index < array.length){
            if(array[index] < array[index - 1]){
                right1 = index - 1;
                left2 = index;
            }
            index++;
        }
        targetIndex = binarySearch(array,left1,right1,target);
        if(targetIndex != -1){
            targetIndex = binarySearch(array,left2,right2,target);
        }
        return targetIndex;
    }

    public static int shiftedBinarySearchOpt(int[] array, int target) {
       int left = 0;
       int right = array.length - 1;
       while(left <= right){
           int middle = (left + right) / 2;
           int potentialMatch = array[middle];
           int leftValue = array[left];
           int rightValue = array[right];
           if(target == potentialMatch){
               return middle;
           } else if (leftValue < potentialMatch) {
               if(target < potentialMatch && target >= leftValue){
                   right = middle - 1;
               }
               else{
                   left = middle + 1;
               }
           }else{
               if(target < potentialMatch && target <= rightValue){
                   left = middle + 1;
               }
               else {
                   right = middle - 1;
               }
           }
       }
       return -1;
    }

    private static int binarySearch(int[] array, int leftIdx, int rightIdx, int target) {
        while (leftIdx <= rightIdx){
            int mid = (leftIdx + rightIdx) / 2;
            int potentialMatch = array[mid];
            if(potentialMatch == target){
                return mid;
            }
            else if(potentialMatch > target){
                rightIdx = mid - 1;
            }
            else {
                leftIdx = mid + 1;
            }
        }
        return -1;
    }

    public static int[] searchInSortedMatrix(int[][] matrix, int target) {
        // Write your code here.
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                if(matrix[row][col] == target){
                    return new int[]{row,col};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static int[] searchInSortedMatrixOpt(int[][] matrix, int target) {
        // Write your code here.
        for(int row = 0; row < matrix.length; row++){
            int[] col = matrix[row];
            int colValue = binarySearchColumn(col,target);
            if(colValue != -1){
                return new int[]{row,colValue};
            }
        }
        return new int[] {-1, -1};
    }

    public static int[] searchInSortedMatrixOptimised(int[][] matrix, int target) {
        // Write your code here.
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] > target){
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            }
            else {
                return new int[]{row,col};
            }
        }
        return new int[] {-1, -1};
    }

    private static int binarySearchColumn(int[] col, int target) {
        int left = 0;
        int right = col.length - 1;
        while( left <= right){
            int mid = (left + right) / 2;
            int potentialMatch = col[mid];
            if(potentialMatch == target){
                return mid;
            } else if (target > potentialMatch) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int[] findThreeLargestNumbers(int[] array) {
        int[] threeLargestNumbers = new int[]{
                Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE
        };
        for(int i: array){
            shiftAndUpdate(i,threeLargestNumbers);
        }
        return threeLargestNumbers;
    }

    private static void shiftAndUpdate(int current, int[] threeLargestNumbers) {
        if(current > threeLargestNumbers[2]){
            update(threeLargestNumbers,current,2);
        } else if (current > threeLargestNumbers[1]) {
            threeLargestNumbers[1] = current;
        }else {
            threeLargestNumbers[0] = current;
        }
    }

    private static void update(int[] threeLargestNumbers, int current, int index) {
        for(int i = 0; i <= index; i++){
            if(i == index){
                threeLargestNumbers[i] = current;
            }
            else {
                threeLargestNumbers[i] = threeLargestNumbers[i + 1];
            }
        }
    }

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            int potentialMatch = array[mid];
            if(potentialMatch == target){
                return mid;
            } else if (potentialMatch > target) {
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
