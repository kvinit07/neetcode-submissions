class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (S > sum || -S < -sum) return 0;
        
        int[] dp = new int[2 * sum + 1];
        dp[sum] = 1;
        
        for (int num: nums) {
            int[] next = new int[2 * sum + 1];
            for (int i = 0; i < dp.length; i++) {
                if (dp[i] == 0) continue;
                next[i + num] += dp[i];
                next[i - num] += dp[i];
            }
            dp = next;
        }
        
        return dp[sum + S];
    }
}