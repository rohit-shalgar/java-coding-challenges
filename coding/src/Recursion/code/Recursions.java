package Recursion.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
