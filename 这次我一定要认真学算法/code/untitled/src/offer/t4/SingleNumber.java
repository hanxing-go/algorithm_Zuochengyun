package offer.t4;

import java.util.Map;

public class SingleNumber {
    public int singleNumber_simple(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = arr[i] & res;
        }

        return res;
    }

    public int singleNumber_complex(int[] arr) {
        int res = 0;
        int[] a = new int[32];
        // 每一个int型的数组都是由32位组成的
        // 也就是说如果某个数字出现了3次，那么这个数字任意的任意第i位之和都能被3整除
        // (1 + 1 + 1) % 3 = 0   (0 + 0 + 0) % 3 = 0;
        for (int i = 0; i < arr.length; i++) {
            int index = 0;
            int j = arr[i];
            while (j != 0) {
                a[index++] += j % 2;
                j /= 2;
            }
        }


        for (int i = 0; i < 32; i++) {
            a[i] = a[i] % 3;
            res += a[i] * Math.pow(2, i);
        }

        return res;
    }

    public int singleNumber_complex1(int[] nums) {
        int[] bitSums = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitSums[i] += (num >> (31 - i)) & 1;
            }
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + bitSums[i] % 3;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a= {3, 1, 3, 1, 3, 1, 100, 100, 100, 6};
        System.out.println(new SingleNumber().singleNumber_complex1(a));
    }
}
