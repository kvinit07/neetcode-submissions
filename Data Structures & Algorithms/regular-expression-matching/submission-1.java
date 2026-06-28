class Solution {
    private int m, n;
    private String s, p;
    private Boolean[][] memo; 

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
        if (j == n) return memo[i][j] = (i == m);

        boolean firstMatch = (i < m) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            boolean res = dfs(i, j + 2) || (firstMatch && dfs(i + 1, j));
            return memo[i][j] = res;
        }
        boolean res = firstMatch && dfs(i + 1, j + 1);
        return memo[i][j] = res;
    }
}