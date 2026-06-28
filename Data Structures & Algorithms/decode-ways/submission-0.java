class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();

        // Rolling DP:
        // next  = dp[i+1] (ways to decode the suffix starting at i+1)
        // next2 = dp[i+2]
        // curr  = dp[i] being computed for the current i
        int next = 1;   // dp[n] = 1 (empty string has one valid way)
        int next2 = 0;  // placeholder; will be set after first iteration
        int curr = 0;

        // Walk from rightmost char towards leftmost
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '0') {
                // A chunk starting with '0' can't map to any letter
                curr = 0;
            } else {
                // Take one digit (1..9) -> contribute dp[i+1]
                curr = next;

                // Try to take two digits if there's a next char and it's within 10..26
                if (i + 1 < n) {
                    char d = s.charAt(i + 1);
                    // Valid pair if: 10..19 (c == '1') OR 20..26 (c == '2' and d <= '6')
                    if (c == '1' || (c == '2' && d <= '6')) {
                        curr += next2; // add dp[i+2]
                    }
                }
            }

            // Slide the DP window left for next iteration:
            next2 = next; // dp[i+2] becomes old dp[i+1]
            next  = curr; // dp[i+1] becomes current dp[i]
        }

        // dp[0]: total decodings for the full string
        return curr;
    }
}