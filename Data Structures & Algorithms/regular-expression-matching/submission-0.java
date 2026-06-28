class Solution {
    private int m, n;
    private String s, p;
    private Boolean[][] memo; // memo[i][j] = does s[i:] match p[j:] ?

    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        this.m = s.length();
        this.n = p.length();
        this.memo = new Boolean[m + 1][n + 1];
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (memo[i][j] != null) return memo[i][j];

        // Base: if pattern consumed, string must also be fully consumed
        if (j == n) return memo[i][j] = (i == m);

        boolean firstMatch = (i < m) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // If next char is '*', we have two choices
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            // 1) Skip `x*` entirely (use zero occurrences)
            // 2) If firstMatch, consume one char from s and keep pattern at j (use one or more)
            boolean res = dfs(i, j + 2) || (firstMatch && dfs(i + 1, j));
            return memo[i][j] = res;
        }

        // Otherwise, they must match now and move both pointers
        boolean res = firstMatch && dfs(i + 1, j + 1);
        return memo[i][j] = res;
    }
}