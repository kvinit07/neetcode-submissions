class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        if (n == 0) return true;
        int minLen = Integer.MAX_VALUE, maxLen = 0;
        for (String w : dict) {
            int len = w.length();
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
        }
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; 

        for (int i = 1; i <= n; i++) {
            int start = Math.max(0, i - maxLen);
            int end = i - minLen; 
            if (end < 0) continue; 

            for (int j = start; j <= end; j++) {
                if (!dp[j]) continue;

                if (dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}