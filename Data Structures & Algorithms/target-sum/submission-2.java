class Solution {
    public int findTargetSumWays(int[] nums, int target) {
      int S = 0;
      for (int x : nums) S += x;
      if ((S + target) % 2 != 0) return 0;
      int k = (S + target) / 2;
      if (k < 0) return 0;
      int[] dp = new int[k+1];
      dp[0] = 1;
      for (int num : nums) {
        for (int s = k; s >= num; s--) {
           dp[s] += dp[s - num];
        }
      }
      return dp[k];
    }
}
