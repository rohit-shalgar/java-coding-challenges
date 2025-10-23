package String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Code {
    public String reverseWordsInString(String input){
        ArrayList<String>words = new ArrayList<>();
        int start = 0;
        int index = 0;
        while(index < input.length()){
            if(input.charAt(index) == ' '){
                words.add(input.substring(start,index));
                start = index;
            }
            if(input.charAt(start) == ' '){
                words.add(" ");
                start++;
            }
            index++;
        }

        words.add(input.substring(start,index));
        Collections.reverse(words);
        return String.join("", words);
    }


    public String reverseWordsInStringOptCap(String input){
        StringBuilder builder = new StringBuilder();
        int inputLength = input.length() - 1;
        int end = inputLength;
        int index = inputLength;
        while(index>= 0){
            if(input.charAt(index) ==  ' '){
                builder.append(input, index + 1, end + 1);
                end = index;
            } else if (input.charAt(end) == ' ') {
                builder.append(" ");
                end = index;
            }
            index--;
        }
        builder.append(input,index+1, end+1);
        return builder.toString();
    }

    public String reverseWordsInStringOptInPlace(String input){
        char[] words = input.toCharArray();
        reverseWord(words,0,words.length - 1);
        int start = 0;
        while(start < words.length){
            int end = start;
            while(end < words.length && words[end] != ' '){
                end++;
            }
            reverseWord(words,start,end - 1);
            start = end + 1;
        }
        reverseWord(words,start,words.length);
        return new String(words);
    }

    private void reverseWord(char[] words, int start, int end) {
        while(start < end){
            char temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
    }
}
