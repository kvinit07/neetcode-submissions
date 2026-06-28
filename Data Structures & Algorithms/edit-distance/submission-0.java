class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        if (n == 0) return m; // insert all of word2
        if (m == 0) return n; // delete all of word1

        // Space optimize: ensure word2 is the shorter (dp size = m+1)
        if (m > n) {
            String tmp = word1; word1 = word2; word2 = tmp;
            int t = n; n = m; m = t;
        }

        char[] A = word1.toCharArray(); // length n
        char[] B = word2.toCharArray(); // length m

        // dp[j] == edit distance between A[0..i-1] and B[0..j-1] for current i
        int[] dp = new int[m + 1];

        // Base row: i = 0 -> convert empty A prefix to B[0..j-1] by j insertions
        for (int j = 0; j <= m; j++) dp[j] = j;

        for (int i = 1; i <= n; i++) {
            int prevDiag = dp[0]; // this holds dp[i-1][0] initially
            dp[0] = i;            // convert A[0..i-1] to empty B by i deletions

            for (int j = 1; j <= m; j++) {
                int temp = dp[j]; // will become prevDiag next iteration (top-left)

                if (A[i - 1] == B[j - 1]) {
                    // Characters match: carry over diagonal
                    dp[j] = prevDiag;
                } else {
                    // 1 + min(replace, insert, delete)
                    int replace = prevDiag + 1; // change A[i-1] -> B[j-1]
                    int insert  = dp[j - 1] + 1; // insert B[j-1] into A
                    int delete  = dp[j] + 1;     // delete A[i-1] from A
                    dp[j] = Math.min(replace, Math.min(insert, delete));
                }

                prevDiag = temp; // shift diagonal for next j
            }
        }
        return dp[m];
    }
}