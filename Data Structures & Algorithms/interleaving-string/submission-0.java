class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // Length check: s3 must be exactly the combined length
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) return false;

        // Ensure s2 is the shorter one to minimize DP space
        if (m > n) {
            String tmp = s1; s1 = s2; s2 = tmp;
            int t = n; n = m; m = t;
        }

        char[] a = s1.toCharArray(); // possibly longer
        char[] b = s2.toCharArray(); // shorter
        char[] c = s3.toCharArray();

        // dp[j] = can we form c[0..i+j-1] using a[0..i-1] and b[0..j-1]
        boolean[] dp = new boolean[m + 1];

        // Base row: i = 0; only b contributes
        dp[0] = true; // empty + empty -> empty
        for (int j = 1; j <= m; j++) {
            dp[j] = dp[j - 1] && (b[j - 1] == c[j - 1]);
        }

        // Fill rows for i = 1..n
        for (int i = 1; i <= n; i++) {
            // First column: j = 0; only a contributes
            dp[0] = dp[0] && (a[i - 1] == c[i - 1]);

            // Inner cells
            for (int j = 1; j <= m; j++) {
                char need = c[i + j - 1];
                boolean fromA = dp[j] && (a[i - 1] == need);      // use a[i-1]
                boolean fromB = dp[j - 1] && (b[j - 1] == need);  // use b[j-1]
                dp[j] = fromA || fromB;
            }
        }
        return dp[m];
    }
}