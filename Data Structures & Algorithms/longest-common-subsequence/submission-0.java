class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        if (text2.length() > text1.length()) {
            String tmp = text1;
            text1 = text2;
            text2 = tmp;
        }

        char[] a = text1.toCharArray();
        char[] b = text2.toCharArray();  
        int m = a.length;
        int n = b.length;
        int[] dp = new int[n + 1];

        // Iterate rows over a (text1).
        for (int i = 1; i <= m; i++) {
            int prevDiag = 0; 
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; 
                if (a[i - 1] == b[j - 1]) {
                    dp[j] = prevDiag + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prevDiag = temp;
            }
        }
        return dp[n];
    }
}