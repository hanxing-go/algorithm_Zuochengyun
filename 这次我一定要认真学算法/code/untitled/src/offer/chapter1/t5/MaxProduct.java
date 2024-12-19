package offer.chapter1.t5;

public class MaxProduct {
    // 计算长度乘积
    public int maxProduct(String[] words) {
        boolean[][] flags = new boolean[words.length][26];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                flags[i][c - 'a'] = true;
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int k = 0;
                for (; k < 26; k++) {
                    // 一旦出现一个相同的，马上下一个字符串
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }

                if (k == 26) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }

        return result;
    }

    public int maxProduct1 (String[] words) {
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char ch: words[i].toCharArray()) {
                flags[i] |= 1 << (ch - 'a');
            }
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((flags[i] & flags[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"abcw", "foo", "bar", "fxyz", "abcdef"};
        System.out.println(new MaxProduct().maxProduct(words));
    }
}
