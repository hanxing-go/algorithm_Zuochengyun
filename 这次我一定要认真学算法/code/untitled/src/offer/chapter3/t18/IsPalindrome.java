package offer.chapter3.t18;

public class IsPalindrome {
    public boolean ispalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left < right && Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }
}
