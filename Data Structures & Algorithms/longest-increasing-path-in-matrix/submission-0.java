class Solution {
    private static final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
    private int m, n;
    private int[][] grid;
    private int[][] memo; 

    public int longestIncreasingPath(int[][] matrix) {
        // Edge case: empty input
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        this.m = matrix.length;
        this.n = matrix[0].length;
        this.grid = matrix;
        this.memo = new int[m][n]; // 0 means "not computed yet"

        int ans = 0;
        // Try starting from every cell; take the maximum path length found
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, dfs(r, c));
            }
        }
        return ans;
    }

    // Returns the longest strictly increasing path length starting at (r, c)
    private int dfs(int r, int c) {
        // If already computed, reuse it (memoization)
        if (memo[r][c] != 0) return memo[r][c];

        int best = 1;            // At minimum, the path is the cell itself
        int curVal = grid[r][c]; // Current cell value

        // Explore all 4 neighbors that are strictly larger
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue; // bounds
            if (grid[nr][nc] > curVal) {
                best = Math.max(best, 1 + dfs(nr, nc));
            }
        }

        memo[r][c] = best; // Cache result before returning
        return best;
    }
}