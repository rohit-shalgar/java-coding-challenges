import java.util.List;

public class Tests {

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

    public static void main(String[] args) {
        int[][] input = new int[4][3];
        for(int row = 0; row < input.length; row++){
            for(int col = 0; col < input[0].length; col++){
                input[row][col] = 1;
            }
        }
        int countPath = countPathsToBottom(input);
        System.out.println(countPath);
    }
}
