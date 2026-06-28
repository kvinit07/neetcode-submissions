class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int S = 0;
        for (int x : nums) S += x;
        if ((S + target) % 2 != 0) return 0;
        int K = (S + target) / 2;
        if (K < 0) return 0;
        int[] dp = new int[K + 1];
        dp[0] = 1; 

        for (int num : nums) {
            for (int s = K; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        return dp[K];
    }
}