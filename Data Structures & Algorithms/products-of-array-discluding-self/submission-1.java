class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // 1) Build prefix products in ans
        ans[0] = 1; // no elements to the left of index 0
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // 2) Multiply by suffix products on the fly (right to left)
        int suffix = 1; // no elements to the right of last index
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * suffix;
            suffix *= nums[i];
        }

        return ans;
    }
}