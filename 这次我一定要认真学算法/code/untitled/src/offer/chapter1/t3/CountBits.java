package offer.chapter1.t3;

public class CountBits {
    public int[] countBits1(int num) {
        // 计算整数i的二进制形式中1的个数有多种不同的方法，其中一种比较高效的方法是每次用"i & (i - 1)" 将整数i的最右边的1变成0
        // 对i和i-1进行位与运算，相当于将其最右边的1变成0
        // 以二进制的1100为例，它减去1的结果是1011.1100和1011的位与运算结果正好是1000.
        int result[] = new int[num + 1];
        for (int i = 0; i < num ; i++) {
            int j = i;
            while (j != 0) {
                j = j & (j - 1);
                result[i] ++;
            }
        }

        return result;
    }
    // 如果一个整数共有k位，那么它的二进制形式中可能有O(k)个1.在上述代码中

    public int[] countBits2(int num) {
        // 根据countBits1的规律，我们可以知道，j 比 j & (j - 1) 多了一位1
        int[] result = new int[num + 1];
        for (int i = 0; i < num; i ++) {
            // 因此我们可以采用动态规划的思想
            result[i] = result[i & (i - 1)] + 1;
        }

        return result;
    }

    public int[] countBits3(int num) {
        // 如果正整数i是一个偶数，那么将 i / 2左移一位，就可以得到 i 。 所以 i 和 i / 2的 1的个数是相同的
        // 如果是奇数的话，那么i相当于 i / 2左移一位之后再将最右边一位设为 1的结果，，因此 i 比 i / 2的 1的个数多1。
        int[] result = new int[num];
        for (int i = 0; i < num; i ++) {
            result[i] = i % 2 == 0 ? result[i / 2] : result[i / 2] + 1;

            // 书里展示了一种更加高效的写法
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }
}
