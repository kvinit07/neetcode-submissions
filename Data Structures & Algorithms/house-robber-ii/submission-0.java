class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int caseA = robRange(nums, 0, n - 2);
        int caseB = robRange(nums, 1, n - 1);

        return Math.max(caseA, caseB);
    }

    private int robRange(int[] nums, int l, int r) {
        int prev2 = 0; // best up to i-2
        int prev1 = 0; // best up to i-1
        for (int i = l; i <= r; i++) {
            int take = prev2 + nums[i];
            int skip = prev1;
            int curr = Math.max(skip, take);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}