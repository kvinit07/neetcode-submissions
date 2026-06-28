class Solution {
    public int coinChange(int[] coins, int amount) {
      if (amount == 0) return 0;
      final int INF =  amount + 1;
      int[] dp = new int[amount + 1];
      Arrays.fill(dp, INF);
      dp[0] = 0;
      for (int i = 0; i <= amount; i++) {
        for (int c : coins) {
          if (c <= i) {
            dp[i] = Math.min(dp[i], dp[i-c]+1);
          }
        }
      }
      return dp[amount] > amount ? -1 : dp[amount];
    }
}
