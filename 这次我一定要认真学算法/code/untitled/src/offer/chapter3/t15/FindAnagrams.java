package offer.chapter3.t15;

import java.util.ArrayList;
import java.util.List;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> indexList = new ArrayList<>();
        if (s1.length() > s2.length()) {
            return indexList;
            // 不可能存在变位词，直接返回空的List
        }
        int[] hashTable = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            hashTable[s1.charAt(i) - 'a']++;
            hashTable[s2.charAt(i) - 'a']--;
        }

        if (checkZero(hashTable)) {
            indexList.add(0);
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            hashTable[s2.charAt(i) - 'a']--;
            hashTable[s2.charAt(i - s1.length()) - 'a']++;

            if (checkZero(hashTable)) {
                indexList.add(i - s1.length() + 1);
            }
        }
        return indexList;
    }

    private boolean checkZero(int[] hashTable) {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("ac", "adsscahjkhkajddcaac"));
    }
}
