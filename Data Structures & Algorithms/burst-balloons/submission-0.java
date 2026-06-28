class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] val = new int[n + 2];
        val[0] = 1; val[n + 1] = 1;
        for (int i = 0; i < n; i++) val[i + 1] = nums[i];

        int[][] dp = new int[n + 2][n + 2]; 
        for (int len = 2; len <= n + 1; len++) {
            for (int l = 0; l + len <= n + 1; l++) {
                int r = l + len;
                int best = 0;
                for (int k = l + 1; k < r; k++) {
                    int gain = dp[l][k] + val[l] * val[k] * val[r] + dp[k][r];
                    if (gain > best) best = gain;
                }
                dp[l][r] = best;
            }
        }
        return dp[0][n + 1];
    }
}