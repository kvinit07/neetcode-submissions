class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        if (m == 0) return 1;          // empty t can be formed in exactly 1 way
        if (n < m) return 0;           // s too short to form t
        long[] dp = new long[m + 1];
        dp[0] = 1; // base: there is exactly 1 way to form empty t from any prefix of s

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        for (int i = 1; i <= n; i++) {
            // Traverse t backward so S[i-1] is used at most once in this iteration
            for (int j = m; j >= 1; j--) {
                if (S[i - 1] == T[j - 1]) {
                    dp[j] += dp[j - 1];
                }
            }
            // dp[0] is always 1 (empty t)
        }

        long ans = dp[m];
        return (ans > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) ans;
    }
}