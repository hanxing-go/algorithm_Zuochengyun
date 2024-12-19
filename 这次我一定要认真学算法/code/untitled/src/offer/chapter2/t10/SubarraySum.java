package offer.chapter2.t10;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }

        // 先进行扫描，计算出前缀和
        int[] subSum = new int[nums.length];
        subSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            subSum[i] = subSum[i - 1] + nums[i];
        }

        int count = 0;
        int left = 0;
        for (int right = 0; right < subSum.length; right++) {
            while ((left <= right) && (subSum[right] - subSum[left] + nums[right] > k)) {
                // 如果子数组之和大于等于k，那么我们应该要右移我们的left指针
                left++;
            }

            while ((left <= right) && (subSum[right] - subSum[left] + nums[right] ==k)) {
                count++;
                left++;
            }

        }

        return count;
    }

    public int subarraySum1(int[] nums, int k) {
        Map<Integer,  Integer> sumToCount = new HashMap<>();
        sumToCount.put(0, 1);
        // key 存放的是和， value存放的是个数
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            count += sumToCount.getOrDefault(sum - k, 0);
            sumToCount.put(sum, sumToCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 1, 1, 1};
        System.out.println(new SubarraySum().subarraySum(a, 3));

    }
}
