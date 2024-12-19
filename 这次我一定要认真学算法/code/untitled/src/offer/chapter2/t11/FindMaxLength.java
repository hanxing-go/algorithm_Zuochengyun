package offer.chapter2.t11;

import java.util.HashMap;
import java.util.Map;

public class FindMaxLength {
    public int findMaxLength(int[] nums) {
        int maxLength = 0;

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        // 健为和， 值为下标
        sumToIndex.put(0, -1);
        // 下标从 -1 开始
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (sumToIndex.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
                // 如果存在的话，肯定记录最左边的值，这样的话才可以计算最长的
            } else {
                sumToIndex.put(sum, i);
            }
        }


        return maxLength;
    }
}
