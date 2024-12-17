package offer.t7;

import java.util.*;

public class ThreeSum {
    // 相当于t6的进阶版
    // 可以固定好一个数 x，然后找有没有两个数相加为 -x。
    // 但是题目没有说数组是排序好了的，所以我们要先对数组进行排序

    // 请注意： 由于返回值中不能包含重复的三元组，因此我们要过滤掉相同的三元组
    // 怎么过滤？ 保证 nums[i] + nums[j] + nums[k] 中的nums[i] 和 nums[j] 不重复就可以啦
    public List<List<Integer>> threeSum(int[] nums) {
        // 先对数组进行排序
        Arrays.sort(nums);
        // 建立返回的List
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int p1 = i + 1;
            int p2 = nums.length - 1;
            int target = - nums[i];
            while (p1 < p2) {
                if (nums[p1] + nums[p2] == target) {
                    List<Integer> res = new ArrayList<>();
                    Collections.addAll(res, nums[i], nums[p1], nums[p2]);
                    result.add(res);
                } else if (nums[p1] + nums[p2] < target) {
                    p1++;
                } else {
                    p2--;
                }

                // 同理，要过滤掉相同的nums[p1]
                int tmp = nums[p1];
                while (p1 + 1 < p2 && nums[p1 + 1] == tmp ) {
                    p1++;
                }
            }

            // 过滤掉相同的nums[i]，如果数组的下一位数和当前数相同，那么就移动指针到下一位
            while (i + 1 < nums.length && nums[i + 1] == -target) {
                i++;
            }
        }

        return result;
    }
}
