package offer.chapter2.t9;

public class NumSubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        long product = 1;
        int left = 0;
        int count = 0;

        for (int right = 0; right < nums.length; ++right) {
            product *= nums[right];

            // 固定好最右边 right指针，然后遍历找到left的位置
            while (left <= right && product >= k) {
                product /= nums[left++];
            }

            // 只需要计算出right 与 left 之间的差就可以啦
            count += right >= left ? right - left + 1 : 0;
        }

        return count;
    }
}
