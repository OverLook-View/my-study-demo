package com.study.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchSimilarWords {

    public static void main(String[] args) {

    }

    private static void printHighChangeables(Map<String, List<String>> adjWords, int minWords) {
        for (Map.Entry<String, List<String>> entry : adjWords.entrySet()) {
            List<String> words = entry.getValue();
            if (words.size() > minWords) {
                System.out.print(entry.getKey() + "(");
                System.out.print(words.size() + "):");
                for (String word : words) {
                    System.out.print(" " + word);
                }
                System.out.println();
            }
        }
    }

    private static boolean oneCharOff(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int diffs = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++diffs > 1)
                    return false;
            }
        }
        return diffs == 1;
    }

    private static Map<String, List<String>> computeAdjacentWords(List<String> theWords) {
        Map<String, List<String>> adjWords = new TreeMap<>();
        String[] words = theWords.toArray(new String[theWords.size()]);
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (oneCharOff(words[i], words[j])) {
                    update(adjWords, words[i], words[j]);
                    update(adjWords, words[j], words[i]);
                }
            }
        }
        return adjWords;
    }

    private static <KeyType> void update(Map<KeyType, List<String>> m, KeyType key, String value) {
        List<String> lst = m.get(key);
        if (lst == null) {
            lst = new ArrayList<>();
            m.put(key, lst);
        }
        lst.add(value);
    }

    public static Map<String,List<String>> computeAdjacentWords2(List<String> words){
        Map<String,List<String>> adjWords=new TreeMap<>();
        Map<Integer,List<String>> wordsByLength=new TreeMap<>();

        for (String word : words) {
            update(wordsByLength,word.length(),word);
        }
        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()) {

            List<String> groupsWrods = entry.getValue();
            Integer groupNum = entry.getKey();

            for (int i = 0; i < groupNum; i++) {
                Map<String,List<String>> repToWord=new TreeMap<>();

                for (String str : groupsWrods) {
                    String rep = str.substring(0, i) + str.substring(i + 1);
                    update(repToWord,rep,str);
                }

                for (List<String> wordClique : repToWord.values()) {
                    if (wordClique.size()>2)
                        for (String s1 : wordClique) {
                            for (String s2 : wordClique) {
                                if (!s1.equals(s2))
                                    update(adjWords,s1,s2);
                            }
                        }
                }
            }
        }
        return adjWords;
    }
}
