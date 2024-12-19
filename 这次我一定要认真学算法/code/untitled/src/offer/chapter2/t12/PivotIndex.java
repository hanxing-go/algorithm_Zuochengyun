package offer.chapter2.t12;

public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int index = -1;
        int sum = 0;
        // 这道题的关键在于，我初次想法是空间复杂度也为O(N)
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i - 1];
            if (total - sum - nums[i] == sum) {
                return i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] a = {1, 7, 3, 6, 2, 9};
        System.out.println(new PivotIndex().pivotIndex(a));
    }
}
