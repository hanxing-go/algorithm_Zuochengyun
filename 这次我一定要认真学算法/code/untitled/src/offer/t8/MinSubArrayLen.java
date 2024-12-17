package offer.t8;

public class MinSubArrayLen {
    public int minSubArrayLen_false(int k, int[] nums) {
        // 用双指针就是了

        // 先计算数组的总和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 定义双指针
        int p1 = 0;
        int p2 = nums.length -1;
        while (p1 < p2) {
            // 先要比较两边指针谁更大
            int maxp = nums[p1] > nums[p2] ? p1 : p2;
            if (maxp == p1 && sum - nums[p1] >= k) {
                p1++;
                sum -= nums[p1];
            } else if (maxp == p2 && sum - nums[p2] >= k) {
                p2--;
                sum -= nums[p2];
            } else {
                break;
            }
        }

        return p2 - p1 + 1;
    }

    // 我的做法是错误的
    // {2, 3, 1, 2, 4, 3},  k = 7 比如对于这样的输入,就有明显的问题

    // 正确的思路应该是滑动窗口
    public int minSubArrayLen(int k, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (left <= right && sum >= k) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        // 甚至判断了一下有没有不存在的情况
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
