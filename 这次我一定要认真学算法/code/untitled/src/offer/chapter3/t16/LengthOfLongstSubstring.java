package offer.chapter3.t16;

public class LengthOfLongstSubstring {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        int[] hashTable = new int[256];
        hashTable[s.charAt(0)]++;

        // 用双指针做
        for (int left = -1, right = 0; right < s.length();) {
            if (checkThanOne(hashTable)) {
                maxLength = Math.max(maxLength, right - left);
                right++;
                if (right < s.length()) {
                    hashTable[s.charAt(right)]++;
                }
            } else {
                hashTable[s.charAt(++left)]--;
            }
        }

        return maxLength;
    }

    private boolean checkThanOne(int[] hashTable) {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] > 1) {
                return false;
            }
        }
        return true;
    }

    // 对是对了，但是我的代码并不优美
    public int lengthOfLongestSubString1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] counts = new int[256];
        int i = 0;
        int j = -1;
        int longest = 1;
        for (; i < s.length(); ++i) {
            counts[s.charAt(i)]++;
            while (checkThanOne(counts)) {
                ++j;
                counts[s.charAt(j)]--;
            }

            longest = Math.max(i - j, longest);
        }

        return longest;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] counts = new int[256];
        int i = 0;
        int j = -1;
        int longest = 1;
        for (; i < s.length(); ++i) {
            counts[s.charAt(i)]++;
            if (counts[s.charAt(i)] == 2) {
                // 出现了一个等于2的，那么就移动j
                while (counts[s.charAt(i)] == 2) {
                    counts[s.charAt(++j)]--;
                }
            }

            longest = Math.max(i - j, longest);
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongstSubstring().lengthOfLongestSubstring2("abcegddefgc"));
    }
}
