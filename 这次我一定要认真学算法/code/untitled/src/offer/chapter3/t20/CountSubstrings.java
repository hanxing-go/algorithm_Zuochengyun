package offer.chapter3.t20;

public class CountSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            count += countPalindrome(s, i , i);
            // 考虑长度为奇数的子串
            count += countPalindrome(s, i, i + 1);
            // 考虑长度为偶数的子串
        }

        return count;
    }

    private int countPalindrome(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }

        return count;
    }
}
