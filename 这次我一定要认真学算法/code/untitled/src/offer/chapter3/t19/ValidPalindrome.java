package offer.chapter3.t19;

public class ValidPalindrome {
    public static Boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return isPalindrome(left + 1, right, s) || isPalindrome(left, right - 1, s) ;
            }
        }

        return true;
    }

    private static boolean isPalindrome(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abccea";
        System.out.println(validPalindrome(s));
    }
}
