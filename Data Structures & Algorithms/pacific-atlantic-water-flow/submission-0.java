class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
                int m = heights.length;
        int n = heights[0].length;

        // Two boolean grids to track cells reachable from Pacific and Atlantic
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Perform DFS from all Pacific-border cells (top row and left column)
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, Integer.MIN_VALUE); // Left column
            dfs(heights, atlantic, i, n - 1, Integer.MIN_VALUE); // Right column
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, Integer.MIN_VALUE); // Top row
            dfs(heights, atlantic, m - 1, j, Integer.MIN_VALUE); // Bottom row
        }

        // Collect cells that can reach both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    // DFS helper function
    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int prevHeight) {
        int m = heights.length;
        int n = heights[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n) return;
        if (visited[r][c]) return;
        if (heights[r][c] < prevHeight) return; // Can't flow uphill in reverse

        visited[r][c] = true;
        dfs(heights, visited, r + 1, c, heights[r][c]);
        dfs(heights, visited, r - 1, c, heights[r][c]);
        dfs(heights, visited, r, c + 1, heights[r][c]);
        dfs(heights, visited, r, c - 1, heights[r][c]);

    }
}
