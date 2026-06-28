class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
      int m = heights.length;
      int n = heights[0].length;
      boolean[][] pacific = new boolean[m][n];
      boolean[][] atlantic = new boolean[m][n];
      for (int c = 0; c < n; c++) {
        dfs(0, c, pacific, heights);
      }
      for (int r = 0; r < m; r++) {
        dfs(r, 0, pacific, heights);
      }
      for (int c = 0; c < n; c++) {
        dfs(m-1, c, atlantic, heights);
      }
      for (int r = 0; r < m; r++) {
        dfs(r, n - 1, atlantic, heights);
      }
      List<List<Integer>> res = new ArrayList<>();
      for (int r = 0; r < m; r++) {
        for (int c = 0; c < n; c++) {
           if (pacific[r][c] && atlantic[r][c]) {
             res.add(Arrays.asList(r,c));
           }
        }
      }
      return res;
    }
    private void dfs(int r, int c, boolean[][] seen, int[][] heights) {
        int m = heights.length, n = heights[0].length;
        if (seen[r][c]) return;
        seen[r][c] = true;
        int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
         if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
         if (seen[nr][nc]) continue;
         if (heights[nr][nc] >= heights[r][c]) {
            dfs(nr, nc, seen, heights);
         }
        }
    }
}
