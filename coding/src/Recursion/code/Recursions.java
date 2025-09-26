package Recursion.code;

import java.util.*;

public class Recursions {
    public static int productSum(List<Object> array) {
        return productSumHelper(array,1);
    }

    private static int productSumHelper(List<Object> array, int multiplier) {
        int sum = 0;
        for(Object object:array){
            if(object instanceof List){
                List<Object> innerList = (ArrayList<Object>)object;
                sum += productSumHelper(innerList, multiplier + 1);
            }
            else {
                sum += (int)object;
            }
        }
        return sum * multiplier;
    }

    public static List<List<Integer>> getPermutations(List<Integer> array) {
        List<List<Integer>>permutations = new ArrayList<>();
        getPermutationsHelper(permutations,array,new ArrayList<Integer>());
        return permutations;
    }

    //try iterative get perm next time
    private static void getPermutationsHelper(List<List<Integer>> permutations,
                                              List<Integer> array,
                                              ArrayList<Integer> currentPermutation) {
        if(array.isEmpty() && !currentPermutation.isEmpty()){
            permutations.add(currentPermutation);
            return;
        }
        for(int i = 0; i < array.size(); i++){
            List<Integer>newArray = new ArrayList<>(array);
            int element = newArray.remove(i);
            currentPermutation.add(element);
            getPermutationsHelper(permutations,newArray,currentPermutation);
        }
    }

    public static List<List<Integer>> powerset(List<Integer> array) {
        List<List<Integer>> powerSets = new ArrayList<>();
        powerSets.add(new ArrayList<>());
        for(int element: array){
            int length = powerSets.size();
            for(int index = 0; index < length; index++){
                List<Integer> current = new ArrayList<>(powerSets.get(index));
                current.add(element);
                powerSets.add(current);
            }
        }
        return powerSets;
    }

    private static final Map<Character, String[]> PHONE_NUMBERS_DATA = new HashMap<>();

    static {
        PHONE_NUMBERS_DATA.put('0', new String[]{"0"});
        PHONE_NUMBERS_DATA.put('1', new String[]{"1"});
        PHONE_NUMBERS_DATA.put('2', new String[]{"a", "b", "c"});
        PHONE_NUMBERS_DATA.put('3', new String[]{"d", "e", "f"});
        PHONE_NUMBERS_DATA.put('4', new String[]{"g", "h", "i"});
        PHONE_NUMBERS_DATA.put('5', new String[]{"j", "k", "l"});
        PHONE_NUMBERS_DATA.put('6', new String[]{"m", "n", "o"});
        PHONE_NUMBERS_DATA.put('7', new String[]{"p", "q", "r", "s"});
        PHONE_NUMBERS_DATA.put('8', new String[]{"t", "u", "v"});
        PHONE_NUMBERS_DATA.put('9', new String[]{"w", "x", "y", "z"});
    }

    public ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        ArrayList<String> phoneNumberMnemocs = new ArrayList<>();
        phoneNumberMnemocsHelper(phoneNumberMnemocs,0,phoneNumber,new StringBuilder());
        return phoneNumberMnemocs;
    }

    private void phoneNumberMnemocsHelper(ArrayList<String> phoneNumberMnemocs,
                                          int idx,
                                          String phoneNumber,
                                          StringBuilder current) {
        if(idx == phoneNumber.length()){
            phoneNumberMnemocs.add(current.toString());
        }
        String[] combinations = PHONE_NUMBERS_DATA.get(phoneNumber.charAt(idx));
        for(String string:combinations){
            current.append(string);
            phoneNumberMnemocsHelper(phoneNumberMnemocs, idx + 1,phoneNumber,current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public float blackjackProbability(int target, int startingHand) {
       if(startingHand > target){
           return 1;
       }
       if(startingHand + 4 >= target){
           return 0;
       }
       float probability = 0.1f;
       for(int i = 1 ; i <= 10; i++){
           int currentHand = startingHand + i;
           probability += (float) (0.1 * blackjackProbability(target,currentHand));
       }
       return probability;
    }

    public float blackjackProbabilityOpt(int target, int startingHand) {
        HashMap<Integer,Float> memoize = new HashMap<Integer, Float>();
        return blackjackProbabilityHelper(target,startingHand,memoize);
    }

    private float blackjackProbabilityHelper(int target, int startingHand, HashMap<Integer, Float> memoize) {
        if(memoize.containsKey(startingHand)){
            return memoize.get(startingHand);
        }
        if(startingHand > target){
            return 1;
        }
        if(startingHand + 4 >= target){
            return 0;
        }
        float probability = 0.0f;
        for(int i = 1; i <= 10; i++){
            int currentHand = startingHand + 1;
            probability += (float) (0.1 * blackjackProbabilityHelper(target, currentHand, memoize));
        }
        memoize.put(startingHand,probability);
        return probability;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int targetedColor = image[sr][sc];
        if(targetedColor != color){
            dfs(image, sr, sc, color, targetedColor);
        }
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int color, int targetedColor){
        if(image[sr][sc] == targetedColor){
            image[sr][sc] = color;
            if(sr > 0){
                dfs(image, sr - 1, sc, color, targetedColor);
            }
            if(sr < image.length  - 1){
                dfs(image, sr + 1, sc, color, targetedColor);
            }
            if(sc > 0){
                dfs(image, sr , sc - 1, color, targetedColor);
            }
            if(sc < image[0].length  - 1){
                dfs(image, sr, sc + 1, color, targetedColor);
            }
        }
    }

    public int islandPerimeter(int[][] grid) {
        int perimiter = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col ; c++){
                if(grid[r][c] == 1){
                    perimiter += 4;
                    if(r > 0 && grid[r-1][c] == 1){
                        perimiter -= 2;
                    }
                    if(c > 0 && grid[r][c - 1] == 1){
                        perimiter -= 2;
                    }
                }
            }
        }
        return perimiter;
    }

    public int islandPerimeterOpt(int[][] grid) {
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                if(grid[row][col] == 1){
                    return dfs(grid, row, col);
                }
            }
        }
        return 0;
    }

    public int dfs(int[][]grid, int row, int col){
        int per = 0;
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return 1;
        }
        if(grid[row][col] == 0){
            return 1;
        }
        if(grid[row][col] == -1){
            return 0;
        }
        grid[row][col] = -1;
        per += dfs(grid, row + 1, col);
        per += dfs(grid, row - 1, col);
        per += dfs(grid, row, col + 1);
        per += dfs(grid, row, col - 1);
        return per;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxIslandArea = 0;
        for(int row = 0; row < grid.length;row++){
            for(int col = 0; col < grid[0].length; col++){
                if(grid[row][col] == 1){
                    int currentIslandArea = dfsAreaIsland(grid, row, col);
                    maxIslandArea = Math.max(maxIslandArea, currentIslandArea);
                }
            }
        }
        return maxIslandArea;
    }

    private int dfsAreaIsland(int[][]grid, int row, int col){
        int area = 0;
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return 0;
        }
        if(grid[row][col] == 0){
            return 0;
        }
        area += 1;
        grid[row][col] = 0;
        area += dfsAreaIsland(grid, row + 1, col);
        area += dfsAreaIsland(grid, row - 1, col);
        area += dfsAreaIsland(grid, row, col + 1);
        area += dfsAreaIsland(grid, row, col - 1);
        return area;
    }

    public String[][] revealMinesweeper(String[][] board, int row, int column) {
        if(board[row][column].equals("M")){
            board[row][column] = "X";
            return board;
        }
        HashMap<CellKey,Boolean> isVisited = new HashMap<>();
        dfsMineSweeper(board,row, column,isVisited);
        return board;
    }

    private void dfsMineSweeper(String[][] board, int row, int column, HashMap<CellKey, Boolean> isVisited) {
        if(row < 0 || row >= board.length || column < 0 || column >= board[0].length){
            return;
        }
        CellKey key = new CellKey(row, column);
        if(isVisited.containsKey(key) && isVisited.get(key).equals(true)){
            return;
        }
        isVisited.put(key, true);
        int adj = countAdjusantMines(board,row,column);
        board[row][column] = String.valueOf(adj);
        if(adj == 0){
            dfsMineSweeper(board, row + 1, column, isVisited);
            dfsMineSweeper(board, row - 1, column, isVisited);
            dfsMineSweeper(board, row, column + 1, isVisited);
            dfsMineSweeper(board, row, column - 1, isVisited);
            dfsMineSweeper(board, row - 1, column + 1, isVisited);
            dfsMineSweeper(board, row - 1, column - 1, isVisited);
            dfsMineSweeper(board, row + 1, column + 1, isVisited);
            dfsMineSweeper(board, row + 1, column - 1, isVisited);
        }
    }

    private int countAdjusantMines(String[][] board, int row, int column) {
        int count = 0;
        for(int dr = -1 ; dr <= 1; dr++){
            for(int dc = -1 ; dc <= 1; dc++){
                int currentRow = row + dr;
                int currentCol = column + dc;
                if(currentRow >= 0 && currentRow < board.length && currentCol >= 0 && currentCol < board[0].length ){
                    if(board[currentRow][currentCol].equals("M")){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    class CellKey {
        private final int row;
        private final int col;

        public CellKey(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CellKey key)) return false;
            return row == key.row && col == key.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

}
