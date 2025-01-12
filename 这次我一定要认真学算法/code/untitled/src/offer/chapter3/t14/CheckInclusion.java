package offer.chapter3.t14;

import com.sun.scenario.effect.impl.state.AccessHelper;

public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        // 如果s1 的长度大于s2的长度直接return false
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] hashTable = new int[26];
        // 标记s1中出现过的字符 并且假装移动我们的虚拟指针
        for (int i = 0; i < s1.length(); i++) {
            hashTable[s1.charAt(i) - 'a']--;
            hashTable[s2.charAt(i) - 'a']++;
        }

        // 第一次检查有没有变位词，如果hashTable所有的内容变为0则存在
        if (checkZero(hashTable)) {
            return true;
        }

        // 用双指针的方法找出现在s2中的变位词
        for (int i = s1.length(); i < s2.length(); i++) {
            // 因为两个指针之间的距离是固定的，所以实际我们只需要一个i指针
            hashTable[s2.charAt(i) - 'a']++;
            hashTable[s2.charAt(i - s1.length()) - 'a']--;
            if (checkZero(hashTable)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkZero(int[] hashTable) {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CheckInclusion().checkInclusion("ac", "cddashca"));
    }
}
